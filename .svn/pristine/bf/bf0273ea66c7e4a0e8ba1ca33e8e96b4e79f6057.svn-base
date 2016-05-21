$(document).ready(function() {
    
    $("#transaction_type").change(function() {
        alert("hello");
        $(document).ajaxStart(function() {
            alert("ajax start");
            $("#ajax_spinner").text("Loading...");
        });

        $(document).ajaxComplete(function() {
            $("#ajax_spinner").text("yes");
        });
    });
});