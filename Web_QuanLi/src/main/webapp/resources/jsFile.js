$(".openmodal").click(function (){
    var id = $(this).attr('data-button');
    $('#editcategoryModal').modal('show');
     var id_base = "#id_" + id,
         name_base = "#name_" + id;
    $("#id_modal").val($(id_base).text());
    $("#name_modal").val( $(name_base).text());
    
});