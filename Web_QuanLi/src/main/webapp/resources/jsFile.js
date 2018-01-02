var listAdd = [], listDelete = [], listexist = [];
$(".openmodal").click(function () {
    var id = $(this).attr('data-button');
    $('#editcategoryModal').modal('show');
    var id_base = "#id_" + id,
            name_base = "#name_" + id;
    $("#id_modal").val($(id_base).text());
    $("#name_modal").val($(name_base).text());

});


$('.checkboxchoose').click(function () {
    if ($(this).is(':checked')) {
        listAdd.push($(this).val());
    } else {
        for (var i = 0; i < listAdd.length; i++) {
            if (Number(listAdd[i]) == Number($(this).val()))
            {
                listAdd.splice(i, 1);
            }
        }
    }
});

$("#addRestaurant").click(function () {
    $("#menuadd").val(listAdd.toString());

});
function submitaddbranch() {
    var monan = $("#menuadd").val();
    if (monan == "") {
        alert("Please choose one dishes");
        return false

    }
}
;
$(document).ready(function () {
//    if()
//    listexist = $("#menuexist").val().split(',');

});

$('.updatecheckboxchoose').click(function () {
    if ($(this).is(':checked')) {
        var exist = false;
        for (var i = 0; i < listDelete.length; i++) {
            if (Number(listDelete[i]) == Number($(this).val())) {
                listDelete.splice(i, 1);
                exist = true;
            }
        }
        if (exist != true) {
            listAdd.push($(this).val());
        }
    } else {
        var existadd = false;
        for (var i = 0; i < listAdd.length; i++) {
            if (Number(listAdd[i]) == Number($(this).val()))
            {
                listAdd.splice(i, 1);
                existadd = true;
            }
        }
        if (exist != true) {
            listDelete.push($(this).val());
        }
    }
});


$("#updateRestaurant").click(function () {
    $("#menuadd").val(listAdd.toString());
    $("#menudelete").val(listDelete.toString());
});



$(document).ready(function () {
    $("#logout").click(function () {
        $("#form-logout").submit();
    });

})
//    $(document).on('change', '.btn-file :file', function () {
//        var input = $(this),
//                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
//        input.trigger('fileselect', [label]);
//    });
//
//    $('.btn-file :file').on('fileselect', function (event, label) {
//
//        var input = $(this).parents('.input-group').find(':text'),
//                log = label;
//
//        if (input.length) {
//            input.val(log);
//        } else {
//            if (log)
//                alert(log);
//        }
//
//    });
//    function readURL(input) {
//        if (input.files && input.files[0]) {
//            var reader = new FileReader();
//
//            reader.onload = function (e) {
//                $('#img-upload').attr('src', e.target.result);
//            }
//
//            reader.readAsDataURL(input.files[0]);
//        }
//    }
//
//    $("#imgInp").change(function () {
//        readURL(this);
//    });
//});