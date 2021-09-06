function validateInputField(inputField, regExp) {
    var value = $(inputField).val();
    // var firstNameRegExp = new RegExp('^[A-Za-z]{2,20}$');
    var isValid = regExp.test(value);
    var validationContainer = $(inputField).closest(".validation-container");
    var messageContainer = $(validationContainer).find(".message-container.message-box");

    if (isValid) {
        $(validationContainer).removeClass("invalid");
        $(messageContainer).hide();
    } else {
        $(validationContainer).addClass("invalid");
        $(messageContainer).text("Input is invalid!").show();
    }
}


//input valid: 20-3-2002 invalid input: 33-13-2005, 29-02-2003
function validateDate() {

    var day = $(".date-of-birth #day").val();
    var month = $(".date-of-birth #month").val();
    var year = $(".date-of-birth #year").val();

    var OK;

    if (OK = (year > 1900) && (year < new Date().getFullYear())) {
        if (OK = (month <= 12 && month > 0)) {

            var LeapYear = (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0));

            if (OK = day > 0) {

                if (month == 2) {

                    OK = LeapYear ? day <= 29 : day <= 28;
                } else {
                    if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                        OK = day <= 30;
                    } else {
                        OK = day <= 31;
                    }
                }
            }
        }
    }

    var validationContainer = $("li.date-of-birth .validation-container");
    var messageContainer = $(validationContainer).find(".message-container.message-box");

    if (OK) {
        $(validationContainer).removeClass("invalid");
        $(messageContainer).hide();
    } else {
        $(validationContainer).addClass("invalid");
        $(messageContainer).text("Input is invalid!").show();
    }


}

function validateGender() {

    var isValid = false;

    $("li.gender input").each(function () {

        if ($(this).is(":checked")) {
            isValid = true;
        }


    });

    var validationContainer = $("li.gender .validation-container");
    var messageContainer = $(validationContainer).find(".message-container.message-box");

    if (isValid) {

        console.log("e valid");

        $(validationContainer).removeClass("invalid");
        $(messageContainer).hide();
    } else {

        console.log("e invalid");


        $(validationContainer).addClass("invalid");
        $(messageContainer).text("Input is invalid!").show();
    }

}

function validateForm() {

    // alert("am validat tot");

    $("#firstName").focusout();
    $("#lastName").focusout();
    $("#email").focusout();

    validateDate();

    validateGender();

    validationWasTriggerd = true;


}

function errorsDisplayed() {

    return $(".invalid").length > 0;
}

var validationWasTriggerd = false;

function populateFirstName() {
    var firstName = $("#firstName").val();
    $("h3.js-inject span.firstName").text(firstName);
}

$(document).ready(function () {

    $(".MuiButtonBase-root.MuiTab-root.MuiTab-textColorInherit").click(function (){
        $(".MuiButtonBase-root.MuiTab-root.MuiTab-textColorInherit").removeClass("Mui-selected");
        $(this).addClass("Mui-selected");


        if($(this).hasClass("pending")){
            $(".decidedBet").hide(200);
            $(".pendingBet").show(200);
        } else {
            $(".decidedBet").show(200);
            $(".pendingBet").hide(200);
        }

    });

    $("#firstName").focusout(function () {
        validateInputField(this, new RegExp('^[A-Za-z]{2,20}$'));
    });
    $("#lastName").focusout(function () {
        validateInputField(this, new RegExp('^[A-Za-z]{2,20}$'));
    });
    $("#email").focusout(function () {
        validateInputField(this, new RegExp('^(?!(.*\\.\\.).*)(?=.{6,255}$)[^@\\s]+@[^@\\s,]+\\.[a-zA-Z0-9\\u007f-\\uffff-]{2,}$'));
    });
    $("li.date-of-birth select").focusout(function () {
        validateDate();
    });


    $("#street").focusout(function () {
            validateInputField(this, new RegExp('^[A-Za-z 0-9\\.,]{2,50}$'));
    });

    $("#postalCode").focusout(function () {
            validateInputField(this, new RegExp('^[0-9]{6,6}$'));
    });

    $("#city").focusout(function () {
            validateInputField(this, new RegExp('^[A-Za-z ]{2,20}$'));
    });

    $("#mobileNumber").focusout(function () {
                validateInputField(this, new RegExp('^[0-9]{9,9}$'));
    });



    $("#viewPassword").click(function (e) {


        if ($("#viewPassword").text() == "Show"){
            $("#password").attr("type","text");
            $("#viewPassword").text("Hide");

        }else {
            $("#password").attr("type","password");
            $("#viewPassword").text("Show");
        }
    });


    $(".submitRegistration").click(function (e) {
        e.preventDefault();

        if (!validationWasTriggerd || validationWasTriggerd && errorsDisplayed()) {
            validateForm();
        }

        if (!errorsDisplayed()) {

            if ($(".form-view-1").is(":visible")) {

                $(".form-view-1").hide(500);
                $(".form-view-2").show(500);
                populateFirstName();

            } else if ($(".form-view-2").is(":visible")) {

                $(".form-view-2").hide(500);
                $(".form-view-3").show(500);
                /* populateFirstName();*/

            }


        }
    });
    $("#depositButton").click(function(e){
        e.preventDefault();
        $(".deposit").show();
    });


    $(".event-item button").click(function(e){
        e.preventDefault();
        var card =  $(".event-column.bet-card");
        $(card).show();
        $(".event-item button").removeClass("selected");
        $(this).addClass("selected");

        var eventItem = $(this).closest(".event-item");
        var playerA = $(eventItem).find("p.playerA").text();
        var playerB = $(eventItem).find("p.playerB").text();
        var time = $(eventItem).find("span.time").text();
        var date = $(eventItem).find("span.date").text();
        var odd = $(this).text();
        var option = $(this).siblings("label").text();
        var eventId =$(eventItem).find(".eventId").text();
        console.log("event id : "+eventId);
        var betValue =  $(card).find("span.betValue").text();
        var potentialGain = odd * betValue;

        $(card).find("span.option").text(option);
        $(card).find("input.option").val(option);
        $(card).find(".teams").text(playerA+" : "+playerB);
        $(card).find(".odd").text(odd);
        $(card).find(".dateTime").text(date+" "+time);
        $(card).find(".potentialGain").text(potentialGain.toFixed(2));
        $(card).find("input.eventId").val(eventId);

    });

    $(".event-column.bet-card input.input-bet-value").focusout(function(e) {
        var newBetValue = $(this).val();
        var card =  $(".event-column.bet-card");
        $(card).find("span.betValue").text(newBetValue);
        var odd = $(".event-item button.selected").text();
        var potentialGain = odd * newBetValue;
        $(card).find(".potentialGain").text(potentialGain.toFixed(2));
    });
});




