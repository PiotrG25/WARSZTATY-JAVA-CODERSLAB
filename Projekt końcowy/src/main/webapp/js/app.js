$(function(){

    var buttons = $("button");
    var pushed = [];
    var trs = $("tr");

    buttons.each(function(index, element){
        pushed.push(true);

        $(element).css("background-color", "green");
        $(element).on("click", function(){
            change(index);
            if(checkWinCondition()){
                alert("Wygrałeś!!!");//todo warynek wygranej
            }
        });

        var tr = trs.eq(index);
        var tds = tr.children();
        var len = tds.length;

        $(element).on("click", function(){
            for(var i = 0; i < len; i++){
                change(parseInt(tds.eq(i).text()))
            }
        });

        $(element).click();//klikanie każdego
    });

    $("table").remove();

//Takie tam funkcje

    function change(index){
        if(pushed[index] === true){
            buttons.eq(index).css("background-color", "red");
            pushed[index] = false;
        }else{
            buttons.eq(index).css("background-color", "green");
            pushed[index] = true;
        }
    }

    function checkWinCondition(){
        for(var i = 0; i < pushed.length; i++){
            if(pushed[i] === false){
                return false;
            }
        }
        return true;
    }
});

