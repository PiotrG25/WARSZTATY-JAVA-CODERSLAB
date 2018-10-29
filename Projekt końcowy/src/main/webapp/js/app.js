$(function(){
    var buttons = $("button");
    var tds = $("td");

    tds.each(function(index, element){
        element.dataset.currentColor = "green";
    });

    buttons.each(function(index, element){
        var tabOfIndexes = randomIndexes(tds.length);
        $(element).click(function(){
            for(var i = 0; i < tabOfIndexes.length; i++){
                change(tds.eq(tabOfIndexes[i]));
            }
        });
        $(element).click();//clicking each button
    });
});

function change(element){
    if(element.data().currentColor == "green"){
        element.css("background-color", "red");
        element.data().currentColor = "red";
    }else{
        element.css("background-color", "green");
        element.data().currentColor = "green";
    }
}

function randomIndexes(length){
    var tab = [];
    for(var i = 0; i < length; i++){
        tab.push(i);
    }

    var tab2 = [];
    var length2 = Math.floor(Math.random() * length) + 1;

    for(i = 0; i < length2; i++){
        var r = Math.floor(Math.random() * length);
        tab2.push(tab[r]);

        tab[r] = tab[length - 1];
        length--;
    }
    return tab2;
}
