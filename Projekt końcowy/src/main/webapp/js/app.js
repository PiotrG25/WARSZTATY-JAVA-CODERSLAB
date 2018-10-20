$(function(){
    var topLis = $("#topList li");
    var leftLis = $("#leftList li");
    console.log(topLis, leftLis);

    var zIndex = 1;

    topLis.on("click", function(){
        var slider = $(this).find(".slider");
        slider.fadeToggle();
        slider.css("z-index", zIndex++);
    });
    leftLis.on("click", function(){
        var slider = $(this).find(".slider");
        slider.fadeToggle();
        slider.css("z-index", zIndex++);
    });

    var boxes = $(".box");
    boxes.each(function(index, element){
        $(element).text(Math.floor(Math.random() * 100) + 1);
    })
});
