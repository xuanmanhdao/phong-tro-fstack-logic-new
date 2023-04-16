$(document).ready(function () {
  $('#acreage-range').slider();

  $.ajax({
    url: "/api/v1/rest/province",
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
      minPrice = minPriceDefault;
    }
    if (typeof maxPrice === 'undefined') {
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
      minAcreage = minAcreageDefault;
    }
    if (typeof maxAcreage === 'undefined') {
      maxAcreage = maxAcreageDefault;
    }

    let requestSearch = {
      ...(idProvince && { idProvince }),
      ...(idDistrict && { idDistrict }),
      ...(idWard && { idWard }),
      ...(exactAddress && { exactAddress }),
      ...(rentPrice && { rentPrice }),
      ...(minPrice && { minPrice }),
      ...(maxPrice && { maxPrice }),
      ...(acreage && { acreage }),
      ...(minAcreage && { minAcreage }),
      ...(maxAcreage && { maxAcreage })
    };

    let searchParams= new URLSearchParams(requestSearch);
    let queryString=searchParams.toString();
    console.log(queryString);

    window.location.href = `/show-posts?${queryString}`;
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
});