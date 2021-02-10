/* 코드 템플릿 참고 : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 이동욱 저 */
var idCheck = false;
var pwCheck = false;
var emailCheck = false;
var valid = {

    init: function () {
        var _this = this;

        $('#idCheck').on('click', function () {
            _this.duplicateCheck();
        });

        $('#userId').on('propertychange change focusout paste input', function () {
            _this.idValidCheck();
        });

        $('#userPW1').on('propertychange change focusout paste input', function () {
            _this.pw1ValidCheck();
        });

        $('#userPW2').on('propertychange change focusout paste input', function () {
            _this.pw2ValidCheck();
        });

        $('#userEmail').on('propertychange change focusout paste input', function () {
            _this.emailValidCheck();
        });

    },

    // 아이디 중복 검사
    duplicateCheck: function () {
        var identity = $('#userId').val();
        $.ajax({
            type: 'get',
            url: 'api/member/duplicateCheck',
            async: false,
            data: {id: identity},
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (result) {
            if (result) {
                $('#id-check').html("사용 가능한 아이디입니다. :p").css("color", "green");
                idCheck = true;
                // console.log("Id-Check: " + this.idCheck);
            }
        }).fail(function (xhr) {
            $('#id-check').html(xhr.responseText).css("color", "red");
        });
        return idCheck;
    },


    idValidCheck: function () {
        // 영어 소문자와 숫자로 이루어진 최소 6 ~ 최대 20자
        var idRegex = /^[a-z0-9]{6,20}$/g;
        var identifier = $('#userId').val();
        var result = idRegex.test(identifier);

        // console.log("ID: " + identifier);
        // console.log("Result: " + result);


        if (!result) {
            $('#id-check').html("6 ~ 20자의 영어소문자/숫자 조합만 가능합니다.").css("color", "red");
            $('#idCheck').attr('disabled', true);
        }

        if (result) {
            $('#idCheck').attr('disabled', false);
        }

        if (identifier === '') {
            $('#id-check').html("필수 정보입니다.").css("color", "red");
        }
    },


    pw1ValidCheck: function () {
        // 영어 대,소문자, 특수문자, 숫자로 이루어진 10 ~ 20자
        var pwRegex = /^[a-zA-Z0-9~!@#$%^&*()\-_+=\\]{8,20}$/g;
        var pw1 = $('#userPW1').val();

        var result = pwRegex.test(pw1);
        console.log("pw: " + result);

        if (!result) {
            $('#pw-check1').html("공백을 포함하지 않는 영어 대,소문자, 특수문자, 숫자로 이루어진 10 ~ 20자만 입력가능합니다.").css("color", "red");
        } else {
            $('#pw-check1').html("사용가능한 비밀번호입니다. :)").css("color", "green");
        }

        if (pw1 === '') {
            $('#pw-check1').html("필수 정보입니다.").css("color", "red");
        }
    },


    pw2ValidCheck: function () {
        var pw1 = $('#userPW1').val();
        var pw2 = $('#userPW2').val();

        if (pw2 === '') {
            $('#pw-check2').html("필수 정보입니다.").css("color", "red");
        } else if (pw1 === pw2) {
            $('#pw-check2').html("비밀번호가 일치합니다. :)").css("color", "green");
            pwCheck = true;
        } else {
            $('#pw-check2').html("비밀번호가 일치하지 않습니다.").css("color", "red");
        }

    },


    emailValidCheck: function () {
        var emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/g;
        var email = $('#userEmail').val();
        var result = emailRegex.test(email);

        if (email === '') {
            $('#email-check').html("필수 정보입니다.").css("color", "red");
            return;
        }

        if (!result) {
            $('#email-check').html("형식에 맞는 이메일을 입력해주세요.").css("color", "red");
        } else {
            $('#email-check').html("사용 가능한 이메일입니다. :)").css("color", "green");
            emailCheck = true;
        }
    }
}
valid.init();