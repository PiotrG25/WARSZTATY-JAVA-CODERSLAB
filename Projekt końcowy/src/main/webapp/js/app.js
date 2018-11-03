$(function(){

    var buttons = $("button");
    var pushed = [];

    buttons.each(function(index, element){
        pushed.push(true);
        $(element).css("background-color", "green");
        console.log(element, index);
        $(element).click(function(){
            change(index);
            if(checkWinCondition()){
                alert("Wygrałeś!!!");//warynek wygranej
            }
        });
        $(element).click();//klikanie każdego
    });

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

