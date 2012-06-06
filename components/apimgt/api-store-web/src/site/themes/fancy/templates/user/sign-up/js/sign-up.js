$(document).ready(function() {
    $.validator.addMethod("matchPasswords", function(value) {
		return value == $("#newPassword").val();
	}, "The passwords you entered do not match.");

    $("#sign-up").validate({
     submitHandler: function(form) {
       jagg.post("/site/blocks/user/sign-up/ajax/user-add.jag", {
            action:"addUser",
            username:$('#newUsername').val(),
            password:$('#newPassword').val()
        }, function (result) {
            if (result.error == false) {
                jagg.message({content:"User added success",type:"info",cbk:function(){location.href = context;}});

            } else {
                jagg.message({content:result.message,type:"error"});
            }
        }, "json");
     }
    });
    $("#newPassword").keyup(function() {
        $(this).valid();
    });
    $('#newPassword').focus(function(){
        $('#password-help').show();
    });
    $('#newPassword').blur(function(){
        $('#password-help').hide();
    });
});