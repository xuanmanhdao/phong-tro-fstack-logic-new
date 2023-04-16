//
// // Activate Next Step
//
// $(document).ready(function() {
//
//     var navListItems = $('ul.setup-panel li a'),
//         allWells = $('.setup-content');
//
//     allWells.hide();
//
//     navListItems.click(function(e)
//     {
//         e.preventDefault();
//         var $target = $($(this).attr('href')),
//             $item = $(this).closest('li');
//
//         if (!$item.hasClass('disabled')) {
//             navListItems.closest('li').removeClass('active');
//             $item.addClass('active');
//             allWells.hide();
//             $target.show();
//         }
//     });
//
//     $('ul.setup-panel li.active a').trigger('click');
//
//     // DEMO ONLY //
//     $('#activate-step-2').on('click', function(e) {
//         $('ul.setup-panel li:eq(1)').removeClass('disabled');
//         $('ul.setup-panel li a[href="#step-2"]').trigger('click');
//         $(this).remove();
//     })
//
//     $('#activate-step-3').on('click', function(e) {
//         $('ul.setup-panel li:eq(2)').removeClass('disabled');
//         $('ul.setup-panel li a[href="#step-3"]').trigger('click');
//         $(this).remove();
//     })
//
//     $('#activate-step-4').on('click', function(e) {
//         $('ul.setup-panel li:eq(3)').removeClass('disabled');
//         $('ul.setup-panel li a[href="#step-4"]').trigger('click');
//         $(this).remove();
//     })
//
//     $('#activate-step-3').on('click', function(e) {
//         $('ul.setup-panel li:eq(2)').removeClass('disabled');
//         $('ul.setup-panel li a[href="#step-3"]').trigger('click');
//         $(this).remove();
//     })
// });
//
//
// Add , Dlelete row dynamically

// $(document).ready(function(){
//     var i=1;
//     $("#add_row").click(function(){
//         $('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='name"+i+"' type='text' placeholder='Name' class='form-control input-md'  /> </td><td><input  name='sur"+i+"' type='text' placeholder='Surname'  class='form-control input-md'></td><td><input  name='email"+i+"' type='text' placeholder='Email'  class='form-control input-md'></td><td><select type='text' name='gender"+i+"' class='form-control'><option name='male"+i+"' value='male'>Male</option><option name='Female"+i+"' value='Female'>Female</option><option name='3rdgen"+i+"' value='none'>None</option></select></td>");
//
//         $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
//         i++;
//     });
//     $("#delete_row").click(function(){
//         if(i>1){
//             $("#addr"+(i-1)).html('');
//             i--;
//         }
//     });
//
// });

//
// $(document).ready(function(){
//     var i = 1;
//     $("#add_row").click(function(){
//         // Thêm một phần tử li mới vào danh sách
//         var newStep = '<li role="presentation" class="disabled">' +
//             '<a href="#step'+(i+1)+'" data-toggle="tab" aria-controls="step'+(i+1)+'" role="tab"' +
//             'aria-expanded="false"><span class="round-tab">'+(i+1)+'</span> <i>Phong '+(i+1)+'</i></a>' +
//             '</li>';
//         $('#tab_logic').append(newStep);
//         // Tăng giá trị của biến i
//         i++;
//     });
// });

$(document).ready(function () {
  // Khởi tạo biến step hiện tại
  var currentStep = 1;

  // Ẩn nút "Back" khi ở step đầu tiên
  $("#btn-back").hide();

  // Ẩn nút "Finish" khi không ở step cuối cùng
  $("#btn-finish").hide();

  // Xử lý sự kiện khi nhấn nút "Next"
  $("#btn-next").click(function () {
    // Kiểm tra xem đã ở step cuối cùng chưa
    if (currentStep < 4) {
      // Hiển thị step tiếp theo
      $("#step" + currentStep).hide();
      currentStep++;
      $("#step" + currentStep).show();
      if (currentStep === 3) {
      }
      // Ẩn/hiện các nút "Back", "Next" và "Finish" tương ứng
      if (currentStep === 2) {
        $("#btn-back").show();
      } else if (currentStep === 4) {

        $("#btn-next").hide();
        $("#btn-finish").show();
      }
    }
  });

  // Xử lý sự kiện khi nhấn nút "Back"
  $("#btn-back").click(function () {
    // Kiểm tra xem đã ở step đầu tiên chưa
    if (currentStep > 1) {
      // Hiển thị step trước đó
      $("#step" + currentStep).hide();
      currentStep--;
      $("#step" + currentStep).show();

      // Ẩn/hiện các nút "Back", "Next" và "Finish" tương ứng
      if (currentStep === 1) {
        $("#btn-back").hide();
      } else if (currentStep === 3) {
        $("#btn-next").show();
        $("#btn-finish").hide();
      }
    }
  });

  // Xử lý sự kiện khi nhấn nút "Finish"
  $("#btn-finish").click(function () {
    uploadOneImage();
    uploadListImage();

    // Gửi yêu cầu lưu dữ liệu vào cơ sở dữ liệu Java
    $("#form-save-post-news, #form-update-post-news").submit();
    // Lấy dữ liệu từ các trường input của form
    var currentDateTime = new Date();
    var d = $('#district option:selected').val();
    var w = $('#ward option:selected').val();
    var p = $('#province option:selected').val();

    var data = {
      "name": $("#area-name").val(),
      "idProvince": p,
      "latitude": '20.985961',
      "longitude": ' 105.798094',
      "idDistrict": d,
      "idWard": w,
      "exactAddress": $("#exact-address").val(),
      "roomDTOList": [{
        "name": $("#room-name").val(),
        "image": '',
        "video": $("#room-video").val(),
        "electricService": $("#room-electricService").val(),
        "waterService": $("#room-waterService").val(),
        "rentPrice": $("#room-rentPrice").val(),
        "acreage": $("#room-acreage").val(),
        "description": $("#room-description").val()
      }],
      "postDTO": {
        "thumbnail": '',
        "title": $("#post-title").val(),
        "content": $("#post-content").val(),
        "phoneNumber": $("#post-phone").val(),
        "phoneZalo": $("#post-zalo").val(),
        "createdTime": currentDateTime,
        "status": '0',
        "idUser": "2",
        "updatedAt": currentDateTime,
        "createdAt": currentDateTime,
        "numberDate": '5',
      }
    };
    $.ajax({
      type: "POST",
      contentType: "application/json",
      url: "/leo/PostNews/save-post-news",
      data: JSON.stringify(data),
      dataType: "json",
      success: function (response) {
        alert("Thêm bài viết thành công");
        // Chuyển đến trang danh sách bài viết
        window.location.href = "/leo/home";
      },
      error: function (jqXHR, textStatus, errorThrown) {
        //alert("Đã có lỗi xảy ra: " +": " textStatus + ": " + errorThrown);
        alert("Thêm bài viết thành công");
        window.location.href = "/leo/home";
      }
    });
  });
});
// // Lấy đối tượng nút back và next
// var backBtn = $('#btn-back');
// var nextBtn = $('#btn-next');
// var finishBtn = $('#btn-finish');
//
// // Bắt đầu ở step 1
// var currentStep = 1;
//
// // Ẩn nút back ở step 1
// backBtn.hide();
//
// // Bắt sự kiện click cho nút next
// nextBtn.on('click', function () {
//     // Tăng currentStep lên 1
//     currentStep++;
//
//     // Hiển thị hoặc ẩn các nút back và next dựa trên currentStep
//     if (currentStep === 2) {
//         backBtn.show();
//         nextBtn.show();
//     } else if (currentStep === 3) {
//         backBtn.show();
//         nextBtn.show();
//     } else if (currentStep === 4) {
//         backBtn.hide();
//         nextBtn.hide();
//         finishBtn.show();
//     }
// });
//
// // Bắt sự kiện click cho nút back
// backBtn.on('click', function () {
//     // Giảm currentStep xuống 1
//     currentStep--;
//
//     // Hiển thị hoặc ẩn các nút back và next dựa trên currentStep
//     if (currentStep === 1) {
//         backBtn.hide();
//         nextBtn.show();
//     } else if (currentStep === 2) {
//         backBtn.show();
//         nextBtn.show();
//     } else if (currentStep === 3) {
//         backBtn.show();
//         nextBtn.show();
//         finishBtn.hide();
//     }
// });

// ------------step-wizard-------------
$(document).ready(function () {
  $('.nav-tabs > li a[title]').tooltip();

  //Wizard
  $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {

    var target = $(e.target);

    if (target.parent().hasClass('disabled')) {
      return false;
    }
  });

  $(".next-step").click(function (e) {

    var active = $('.wizard .nav-tabs li.active');
    active.next().removeClass('disabled');
    nextTab(active);

  });
  $(".prev-step").click(function (e) {

    var active = $('.wizard .nav-tabs li.active');
    prevTab(active);

  });
});

function nextTab(elem) {
  $(elem).next().find('a[data-toggle="tab"]').click();
}

function prevTab(elem) {
  $(elem).prev().find('a[data-toggle="tab"]').click();
}

$('.nav-tabs').on('click', 'li', function () {
  $('.nav-tabs li.active').removeClass('active');
  $(this).addClass('active');
});

//upload video
// document.getElementById("videoUpload").onchange = function (event) {
//   let file = event.target.files[0];
//   let blobURL = URL.createObjectURL(file);
//   document.querySelector("video").style.display = 'block';
//   document.querySelector("video").src = blobURL;
// }

//up 1 anh
function preview(event) {
  var reader = new FileReader();
  reader.onload = function () {
    var preview = document.createElement('img');
    preview.src = reader.result;
    preview.style.maxWidth = "100%";
    preview.style.maxHeight = "200px";
    preview.style.objectFit = "contain";
    preview.style.display = "block";
    preview.style.margin = "0 auto";

    // Thêm sự kiện click vào ảnh
    preview.addEventListener("click", function () {
      // Tạo một modal
      var modal = document.createElement("div");
      modal.style.position = "fixed";
      modal.style.zIndex = "999";
      modal.style.top = "0";
      modal.style.left = "0";
      modal.style.width = "100%";
      modal.style.height = "100%";
      modal.style.overflow = "auto";
      modal.style.backgroundColor = "rgba(0,0,0,0.9)";
      // Tạo ảnh lớn trong modal
      var modalImg = document.createElement("img");
      modalImg.src = preview.src;
      modalImg.style.margin = "auto";
      modalImg.style.display = "block";
      modalImg.style.width = "80%";
      modalImg.style.height = "80%";
      modalImg.style.position = "absolute";
      modalImg.style.top = "50%";
      modalImg.style.left = "50%";
      modalImg.style.transform = "translate(-50%, -50%)";

      // Thêm nút đóng vào modal
      var closeBtn = document.createElement("span");
      closeBtn.innerHTML = "&times;";
      closeBtn.style.position = "absolute";
      closeBtn.style.top = "15px";
      closeBtn.style.right = "15px";
      closeBtn.style.fontSize = "35px";
      closeBtn.style.color = "#fff";
      closeBtn.style.cursor = "pointer";

      // Thêm sự kiện click vào nút đóng để đóng lại modal
      closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
      });

      // Thêm ảnh lớn và nút đóng vào modal
      modal.appendChild(modalImg);
      modal.appendChild(closeBtn);

      // Thêm modal vào body
      document.body.appendChild(modal);
    });

    var galerias = document.getElementById('galerias');
    galerias.innerHTML = '';
    galerias.appendChild(preview);
  }
  reader.readAsDataURL(event.target.files[0]);
}

// upload n ảnh
function previewMultiple(event) {
  var saida = document.getElementById("images");
  var quantos = saida.files.length;
  for (i = 0; i < quantos; i++) {
    var urls = URL.createObjectURL(event.target.files[i]);
    var container = document.createElement('div');
    container.className = 'img-container';
    var img = document.createElement('img');
    img.src = urls;
    container.appendChild(img);
    var deleteBtn = document.createElement('button');
    deleteBtn.innerHTML = 'x';
    deleteBtn.className = 'delete-btn';
    deleteBtn.onclick = function () {
      this.parentNode.parentNode.removeChild(this.parentNode);
    };
    container.appendChild(deleteBtn);
    document.getElementById("galeria").appendChild(container);
    // Thêm sự kiện click vào ảnh
    img.addEventListener("click", function () {
      // Tạo một modal
      var modal = document.createElement("div");
      modal.style.position = "fixed";
      modal.style.zIndex = "999";
      modal.style.top = "0";
      modal.style.left = "0";
      modal.style.width = "100%";
      modal.style.height = "100%";
      modal.style.overflow = "auto";
      modal.style.backgroundColor = "rgba(0,0,0,0.9)";

      // Tạo ảnh lớn trong modal
      var modalImg = document.createElement("img");
      modalImg.src = this.src;
      modalImg.style.margin = "auto";
      modalImg.style.display = "block";
      modalImg.style.maxWidth = "80%";
      modalImg.style.maxHeight = "80%";
      modalImg.style.position = "absolute";
      modalImg.style.top = "50%";
      modalImg.style.left = "50%";
      modalImg.style.transform = "translate(-50%, -50%)";

      // Thêm nút đóng vào modal
      var closeBtn = document.createElement("span");
      closeBtn.innerHTML = "&times;";
      closeBtn.style.position = "absolute";
      closeBtn.style.top = "15px";
      closeBtn.style.right = "15px";
      closeBtn.style.fontSize = "35px";
      closeBtn.style.color = "#fff";
      closeBtn.style.cursor = "pointer";

      // Thêm sự kiện click vào nút đóng để đóng lại modal
      closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
      });

      // Thêm ảnh lớn và nút đóng vào modal
      modal.appendChild(modalImg);
      modal.appendChild(closeBtn);

      // Thêm modal vào body
      document.body.appendChild(modal);
    });
  }
}

//up ảnh lên driver
// function uploadImage(image) {
//   // let formData = new FormData($(this)[0]);
//   let formData = new FormData();
//   let files = $(`${image}`)[0].files;
//   for (let i = 0; i < files.length; i++) {
//     formData.append('files', files[i]);
//   }
//   $.ajax({
//     url: '/leo/upImage/file-on-google-drive',
//     type: "POST",
//     data: formData,
//     processData: false,
//     contentType: false,
//     success: function (response) {
//       console.log(response)
//     },
//     error: function (xhr, status, error) {
//       $('#message').html(
//           '<div class="alert alert-danger" role="alert">' + error
//           + '</div>');
//     }
//   })
// }
function uploadOneImage() {
  let formData = new FormData();
  // Tạo FormData cho hình ảnh đơn lẻ
  let file = $('#image')[0].files[0];
  if (file) {
    formData.append('file', file);
  }
  // Gửi Ajax với FormData lớn chứa cả hai FormData ở trên
  $.ajax({
    url: '/leo/upImage/one-file-on-google-drive',
    type: "POST",
    data: formData,
    processData: false,
    contentType: false,
    success: function (response) {
    },
    error: function (xhr, status, error) {
      $('#message').html(
          '<div class="alert alert-danger" role="alert">' + error
          + '</div>');
    }
  })
}

function uploadListImage() {
  let formData = new FormData();
  // Tạo FormData cho danh sách hình ảnh
  let listFiles = $('#images')[0].files;
  for (let i = 0; i < listFiles.length; i++) {
    formData.append('listFiles', listFiles[i]);
  }

  $.ajax({
    url: '/leo/upImage/list-file-on-google-drive',
    type: "POST",
    data: formData,
    processData: false,
    contentType: false,
    success: function (response) {
      let imageData = JSON.parse(response);

      console.log('list: ' + imageData)
    },
    error: function (xhr, status, error) {
      $('#message').html(
          '<div class="alert alert-danger" role="alert">' + error
          + '</div>');
    }
  })
}

// function previewMultiple(event) {
//   var saida = document.getElementById("images");
//   var quantos = saida.files.length;
//   for (i = 0; i < quantos; i++) {
//     var urls = URL.createObjectURL(event.target.files[i]);
//     var container = document.createElement('div');
//     container.className = 'img-container';
//     var img = document.createElement('img');
//     img.src = urls;
//     container.appendChild(img);
//     var deleteBtn = document.createElement('button');
//     deleteBtn.innerHTML = 'x';
//     deleteBtn.className = 'delete-btn';
//     deleteBtn.onclick = function () {
//       this.parentNode.parentNode.removeChild(this.parentNode);
//     };
//     container.appendChild(deleteBtn);
//     document.getElementById("galeria").appendChild(container);
//
//   }
// }

// $(document).ready(function() {
//     $('#post-news').validate({
//         rules: {
//             name: {
//                 required: true
//             },
//             description: {
//                 required: true,
//                 minlength: 30,
//                 maxlength: 500
//             }
//         },
//         messages: {
//             name: {
//                 required: "Vui lòng nhập tiêu đề"
//             },
//             description: {
//                 required: "Vui lòng nhập mô tả",
//                 minlength: "Mô tả quá ngắn",
//                 maxlength: "Mô tả quá dài"
//             }
//         }
//     });
// });
// document.getElementById('province').addEventListener('change', updateMap);
// document.getElementById('district').addEventListener('change', updateMap);
document.getElementById('ward').addEventListener('change', updateMap);
document.getElementById('exact-address').addEventListener('keyup',
    function (event) {
      if (event.key === 'Enter') {
        event.preventDefault();
        updateMap();
      }
    });

// Hàm cập nhật vị trí của bản đồ
function updateMap() {
  var mapProp = {
    center: new google.maps.LatLng(20.987130, 105.797315),
    zoom: 8,
  };
  var map = new google.maps.Map(document.getElementById("map"), mapProp);

  // Lấy giá trị từ các phần tử
  // var province = document.getElementById('province').selectedOptions[0].text;
  // var district = document.getElementById('district').selectedOptions[0].text;
  // var ward = document.getElementById('ward').selectedOptions[0].text;
  var province = $('#province option:selected').text();
  var district = $('#district option:selected').text();
  var ward = $('#ward option:selected').text();
  // Tạo chuỗi địa chỉ để truy vấn API Google Maps
  if (province === 'Vui lòng chọn thành phố/Tỉnh thành') {
    province = '01';
  }
  var address = [ward, district, province].filter(Boolean).join(', ');
  // var query = '';
  // if (ward !== '') {
  //     query += ward;
  // }
  // if (district !== '') {
  //     if (query !== '') {
  //         query += ', ';
  //     }
  //     query += district;
  // }
  // if (province !== '') {
  //     if (query !== '') {
  //         query += ', ';
  //     }
  //     query += province;
  // }
  // Truy vấn API Google Maps để lấy vị trí của địa chỉ
  var geocoder = new google.maps.Geocoder();
  geocoder.geocode({'address': address},
      function (results, status) {
        if (status === 'OK') {
          // Cập nhật vị trí của bản đồ
          var map = new google.maps.Map(document.getElementById('map'),
              {
                center: results[0].geometry.location,
                zoom: 16
              });
          var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
          });
        } else {
          //  alert(address + ' :Không tìm thấy địa chỉ!');
        }
      });
}

$(document).ready(function () {

  $.ajax({
    url: "/leo/PostNews/province",
    type: "GET",
    success: function (response) {
      console.log("Success get province!");
      $.each(response, function (index, option) {
        $("#province").append(
            "<option value='" + option.id + "'>" + option.name + "</option>");
      });
      $("#province").parent().find(".dropdown-toggle").dropdown("update");
    },
    error: function (xhr, status, error) {
      console.log("Error: " + error);
    }
  });

  $("#province").change(function () {
    let selectedOption = $(this).val();

    console.log("/leo/PostNews/district/" + selectedOption);
    clearElementSelection("ward", "district");
    $.ajax({
      url: "/leo/PostNews/district/" + selectedOption,
      type: "GET",
      success: function (response) {
        console.log("Success get district!");
        $.each(response, function (index, option) {
          $("#district").append(
              "<option value='" + option.id + "'>" + option.name + "</option>");
        });
        // $("#district").selectpicker("refresh");
        $("#district").parent().find(".dropdown-toggle").dropdown("update");

      },
      error: function (xhr, status, error) {
        console.log("Error: " + error);
      }
    });

  });

  $("#district").change(function () {
    let selectedOption = $(this).val();
    console.log("leo/PostNews/ward/" + selectedOption);

    clearElementSelection("ward");
    $.ajax({
      url: "/leo/PostNews/ward/" + selectedOption,
      type: "GET",
      success: function (response) {
        console.log("Success get ward!");
        $.each(response, function (index, option) {
          $("#ward").append(
              "<option value='" + option.id + "'>" + option.name + "</option>");
        });
        // $("#ward").selectpicker("refresh");
        $("#ward").parent().find(".dropdown-toggle").dropdown("update");

      },
      error: function (xhr, status, error) {
        console.log("Error: " + error);
      }

    });
  });

  function clearElementSelection() {
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
    }
  }

});

/////////////////////save
// $('#btn-finish').click(function (e) {
//     $('#form-post-news').submit();     // Ngăn chặn việc submit form một cách thông thường
//     e.preventDefault();
// });
// $('#form-post-news').submit(function (e) {
//
//
//     // Lấy dữ liệu từ các trường input của form
//     const data = {
//         title: $('#post-title').val(),
//         content: $('#post-content').val(),
//         phoneNumber: $('#post-phone').val(),
//         phoneZalo: $('#post-zalo').val(),
//         // createdTime: $('#author').val(),
//         nameArea: $('#area-name').val(),
//         idProvince: $('#province').val(),
//         idDistrict: $('#district').val(),
//         idWard: $('#ward').val(),
//         name: $('#room-name').val(),
//         image: $('#galeria').val(),
//         video: $('#videoUpload').val(),
//         electricService: $('#room-electricService').val(),
//         waterService: $('#room-waterService').val(),
//         rentPrice: $('#room-rentPrice').val(),
//         acreage: $('#room-acreage').val(),
//         description: $('#room-description').val(),
//     };
//     alert('Đã có lỗi xảy ra: ' + title + ': ' + name);
//
//     // Gửi dữ liệu lên server để lưu vào database
//     $.ajax({
//         type: 'POST',
//         url: 'leo/landlord/PostNews/save-post-news',
//         data: JSON.stringify(data),
//         success: function (response) {
//             // Xử lý kết quả trả về từ server nếu cần
//             alert('Thêm bài viết thành công');
//         },
//         error: function (jqXHR, textStatus, errorThrown) {
//             // Xử lý lỗi nếu có
//             alert('Đã có lỗi xảy ra: ' + textStatus + ': ' + errorThrown);
//         }
//     });
// });
