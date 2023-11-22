$(document).ready(() => {
    $("button").on("click", function() {
        $(this).addClass("circle")
        $(this).children("span").addClass("click")
        setTimeout(function(){
            $(this).removeClass("circle")
            $(this).children("span").removeClass("click")
        }.bind(this),9000)
    })
})