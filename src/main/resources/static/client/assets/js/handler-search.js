$(document).ready(function () {
  $('#acreage-range').slider();
  $.ajax({
    url: "api/v1/rest/post",
    type: "GET",
    success: function (response) {
      renderPosts(response);
    },
    error: function (xhr, status, error) {
      console.log("Error: " + error);
    }
  });

  $.ajax({
    url: "api/v1/rest/province",
    type: "GET",
    success: function (response) {
      console.log("Success get province!");
      $.each(response, function (index, option) {
        $("#property_city__select").append(
            "<option value='" + option.id + "'>" + option.name + "</option>");
      });
      $("#property_city__select").selectpicker("refresh");
    },
    error: function (xhr, status, error) {
      console.log("Error: " + error);
    }
  });

  $("#property_city__select").change(function () {
    let selectedOption = $(this).val();
    clearElementSelection("property_district__select", "property_ward__select");
    console.log("api/v1/rest/district/province/" + selectedOption);
    $.ajax({
      url: "/api/v1/rest/district/province/" + selectedOption,
      type: "GET",
      success: function (response) {
        console.log("Success get district!");
        $.each(response, function (index, option) {
          $("#property_district__select").append(
              "<option value='" + option.id + "'>" + option.name + "</option>");
        });
        $("#property_district__select").selectpicker("refresh");
      },
      error: function (xhr, status, error) {
        console.log("Error: " + error);
      }
    });
  });

  $("#property_district__select").change(function () {
    let selectedOption = $(this).val();
    console.log("api/v1/rest/ward/district/" + selectedOption);
    clearElementSelection("property_ward__select");
    $.ajax({
      url: "/api/v1/rest/ward/district/" + selectedOption,
      type: "GET",
      success: function (response) {
        console.log("Success get ward!");
        $.each(response, function (index, option) {
          $("#property_ward__select").append(
              "<option value='" + option.id + "'>" + option.name + "</option>");
        });
        $("#property_ward__select").selectpicker("refresh");
      },
      error: function (xhr, status, error) {
        console.log("Error: " + error);
      }
    });
  });

  $("#form-search").submit(function (event) {
    event.preventDefault();
    let idProvince = $('select[id="property_city__select"]').val();
    let idDistrict = $('select[id="property_district__select"]').val();
    let idWard = $('select[id="property_ward__select"]').val();
    let exactAddress = $('input[id="exact-address"]').val();

    let rentPrice = "true";
    let priceRange = $("#price-range");
    let priceMinMaxDefault = priceRange.data("slider-value");
    let minPriceDefault = priceMinMaxDefault[0];
    let maxPriceDefault = priceMinMaxDefault[1];
    let priceMinMaxCurrent = priceRange.val().split(",");
    let minPrice = priceMinMaxCurrent[0];
    let maxPrice = priceMinMaxCurrent[1];

    if (minPrice === '') {
      console.log("da vao 1");
      minPrice = minPriceDefault;
    }
    if (typeof maxPrice === 'undefined') {
      console.log("da vao 2");
      maxPrice = maxPriceDefault;
    }

    let acreage = "true";
    let acreageRange = $("#acreage-range");
    let acreageMinMaxDefault = acreageRange.data("slider-value");
    let minAcreageDefault = acreageMinMaxDefault[0];
    let maxAcreageDefault = acreageMinMaxDefault[1];
    let acreageMinMaxCurrent = acreageRange.val().split(",");
    let minAcreage = acreageMinMaxCurrent[0];
    let maxAcreage = acreageMinMaxCurrent[1];

    if (minAcreage === '') {
      console.log("da vao 3");
      minAcreage = minAcreageDefault;
    }
    if (typeof maxAcreage === 'undefined') {
      console.log("da vao 4");
      maxAcreage = maxAcreageDefault;
    }

    console.log("Default price: " + priceMinMaxDefault);
    console.log("Default min: " + minPriceDefault);
    console.log("Default max: " + maxPriceDefault);
    console.log(minPrice);
    console.log(maxPrice);
    console.log(minAcreage);
    console.log(maxAcreage);
    $.ajax({
      url: "/api/v1/rest/post",
      type: "GET",
      data: {
        ...(idProvince && {idProvince}),
        ...(idDistrict && {idDistrict}),
        ...(idWard && {idWard}),
        ...(exactAddress && {exactAddress}),
        ...(rentPrice && {rentPrice}),
        ...(minPrice && {minPrice}),
        ...(maxPrice && {maxPrice}),
        ...(acreage && {acreage}),
        ...(minAcreage && {minAcreage}),
        ...(maxAcreage && {maxAcreage})
      },
      success: function (response) {
        $("#post-new").empty();
        renderPosts(response);
      },
      error: function (xhr) {
        console.log(xhr.responseText);
      }
    });
  });

  function clearElementSelection() {
    try {
      for (let param of arguments) {
        console.log(param);
        let selectElement = document.getElementById(param);
        let selectOptions = selectElement.options;
        for (let i = selectOptions.length - 1; i >= 0; i--) {
          selectOptions[i].remove(); // Xóa tùy chọn
        }
        let defaultOption = document.createElement("option");
        defaultOption.text = "--Chọn một tùy chọn--";
        defaultOption.value = "";
        defaultOption.disabled = true;
        defaultOption.selected = true;
        selectElement.add(defaultOption); // Thêm tùy chọn mặc định
        $(`#${param}`).selectpicker("refresh");
      }
    } catch (e) {
      console.error(e);
    }
  }

  function renderPosts(response) {
    let searchRequest = response.searchRequest;
    let result = "";
    if (response.postResponses.length === 0) {
      let errorMessage = `<div class="row justify-content-center mt-5">
                                 <div class="col-md-12">
                                       <div class="alert alert-danger text-center" role="alert">
                                            <h4 class="alert-heading">Không tìm thấy kết quả</h4>
                                            <p>Xin lỗi, chúng tôi không tìm thấy kết quả phù hợp với yêu cầu của bạn.</p>
                                            <p class="mb-0">Vui lòng thử lại với các tiêu chí tìm kiếm khác hoặc liên hệ với chúng tôi nếu bạn cần hỗ trợ.</p>
                                       </div>
                                 </div>
                              </div>`;
      $("#post-new").append(errorMessage);
    }
    $.each(response.postResponses, function (index, option) {
      let postArea = option.areaResponse;
      let exactAddress = postArea.exactAddress.trim();
      let wardName = postArea.wardName.trim();
      let districtName = postArea.districtName.trim();
      let provinceName = postArea.provinceName.trim();
      let address = `${exactAddress} - ${wardName} - ${districtName} - ${provinceName}`;

      let arrayRoom = option.areaResponse.roomResponses;
      let minAcreage = arrayRoom[0]['acreage'];
      let maxAcreage = arrayRoom[0]['acreage'];
      let minPrice = arrayRoom[0]['rentPrice'];
      let maxPrice = arrayRoom[0]['rentPrice'];

      let thumbnail = option.thumbnail;
      let webContentLink = "";

      if (typeof thumbnail !== 'undefined' && thumbnail !== '') {
        let json = JSON.parse(thumbnail);
        webContentLink = json[0].webContentLink;
      }

      for (let i = 0; i < arrayRoom.length; i++) {
        if (minAcreage > arrayRoom[i]['acreage']) {
          minAcreage = arrayRoom[i]['acreage'];
        }

        if (maxAcreage < arrayRoom[i]['acreage']) {
          maxAcreage = arrayRoom[i]['acreage'];
        }

        if (minPrice > arrayRoom[i]['rentPrice']) {
          minPrice = arrayRoom[i]['rentPrice'];
        }

        if (maxPrice < arrayRoom[i]['rentPrice']) {
          maxPrice = arrayRoom[i]['rentPrice'];
        }
      }
      let formattedMinPrice = minPrice.toLocaleString('vi-VN',
          {style: 'currency', currency: 'VND'});
      let formattedMaxPrice = maxPrice.toLocaleString('vi-VN',
          {style: 'currency', currency: 'VND'});

      if (index > 6) {
        let divAdd = `<div class="col-sm-6 col-md-3 p0">
                            <div class="box-tree more-proerty text-center">
                                <div class="item-tree-icon">
                                    <i class="fa fa-th"></i>
                                </div>
                                <div class="more-entry overflow">
                                    <h5>
                                      <a href="/show-posts?${searchRequest}">
                                         Không thể quyết định ?
                                      </a>
                                    </h5>
                                    <h5 class="tree-sub-ttl">Hiển thị tất cả các bài đăng</h5>
                                    <a href="/show-posts?${searchRequest}"><button class="btn border-btn more-black" value="All properties">Tất cả bài đăng</button></a>
                                </div>
                            </div>
                        </div>`;
        result += divAdd;
        return false;
      }

      let divAdd = `<div class="col-sm-6 col-md-3 p0">
                          <div class="box-two proerty-item">
                              <div class="item-thumb">
                                  <a href="/show-posts/post/${option.id}">
                                      <img src="${webContentLink}">
                                  </a>
                              </div>
                              <div class="item-entry overflow">
                                  <h5>
                                      <a href="/show-posts/post/${option.id}">
                                         ${option.title}
                                      </a>
                                  </h5>
                                  <div class="dot-hr"></div>
                                  <span class="pull-left"><b>Diện tích: </b>${minAcreage}m2 - ${maxAcreage}m2 </span>
                                  <span style="font-weight: 600; color: #FDC600;" class="proerty-price pull-left"><b>Giá: </b>${formattedMinPrice} - ${formattedMaxPrice}</span>
                                  <span class="pull-left" style="font-size: 12px; display: block"><b>Địa chỉ: </b>${address}</span>
                              </div>
                          </div>
                      </div>`;
      result += divAdd;
    });
    $("#post-new").append(result);
  }
});