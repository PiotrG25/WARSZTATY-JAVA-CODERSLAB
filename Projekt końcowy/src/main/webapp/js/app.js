$(function(){
    var buttons = $("button");
    var tds = $("td");
    var maxNumber = 100;
    var goal = random(maxNumber);

    tds.each(function(index, element){
        $(element).text(goal);
        element.dataset.goal = goal;
        element.dataset.other = other(goal, maxNumber);
    });

    buttons.each(function(index, element){
        var tabOfIndexes = randomIndexes(tds.length);
        console.log(tabOfIndexes.length);
        $(element).click(function(){
            for(var i = 0; i < tabOfIndexes.length; i++){
                change(tds.eq(tabOfIndexes[i]));
            }
        })
    })
});

function random(length){
    return Math.floor(Math.random() * length);
}

function change(element){
    if(element.text() == element.data().goal){
        element.text(element.data().other);
    }else{
        element.text(element.data().goal);
    }
}

function other(notThis, length){
    do{
        var other = random(length);
    }while(other === notThis);
    return other;
}

function randomIndexes(length){
    var tab = [];
    for(var i = 0; i < length; i++){
        tab.push(i);
    }

    var tab2 = [];
    var length2 = random(length) + 1;

    for(i = 0; i < length2; i++){
        var r = random(length);
        tab2.push(tab[r]);
        tab[r] = tab[length - 1];
        length--;
    }
    return tab2;
}
