$(function(){
    var topLis = $("#topList li");
    var leftLis = $("#leftList li");
    console.log(topLis, leftLis);

    var zIndex = 1;

    topLis.on("click", function(){
        var slider = $(this).find(".slider");
        slider.slideUp();
        slider.slideDown();
        slider.css("z-index", zIndex++);
    });
    leftLis.on("click", function(){
        var slider = $(this).find(".slider");
        slider.slideUp();
        slider.slideDown();
        slider.css("z-index", zIndex++);
    });

    var topSliders = topLis.find(".slider");
    var leftSliders = leftLis.find(".slider");
    var sliders = [];
    topSliders.each(function(index, el){
        sliders.push(el);
    });
    leftSliders.each(function(index, el){
        sliders.push(el);
    });

    var boxes = $(".box");
    boxes.each(function(index, element){
        $(element).text(Math.floor(Math.random() * 100) + 1);
    });

});

function getOtherThan(notThis){
    do{
        var other = Math.floor(Math.random() * 100) + 1;
    }while(other === notThis);
    return other;
}
