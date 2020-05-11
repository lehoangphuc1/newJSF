$(function() {
    //Take the data from the TR during the event button
    $('.edit').click(function () {
        var tr = $(this).closest("tr");

        var id =  $(tr).find("td").eq(0).text();
        var name = $(tr).find("td").eq(1).text();
        var email = $(tr).find("td").eq(2).text();
        var address = $(tr).find("td").eq(3).text();
        var phone = $(tr).find("td").eq(4).text();

        $('#editEmployeeForm\\:name').val(name);
        $('#editEmployeeForm\\:email').val(email);
        $('#editEmployeeForm\\:address').val(address);
        $('#editEmployeeForm\\:phone').val(phone);
        $('#editEmployeeForm\\:id').val(id);
        //If you need to update the form data and change the button link
       /* $("form#ModalForm").attr('action', window.location.href+'/update/'+id);*/
        /*$("input.btn.btn-info").attr('href', window.location.href+'/update_employee/'+id);*/
    });
  });