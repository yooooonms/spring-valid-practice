/* 코드 템플릿 참고 : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 이동욱 저 */
var index = {
    init: function () {
        var _this = this;


        $('#memberJoinBtn').on('click', function () {
            var validId = idCheck;
            var validPw = pwCheck;
            var validEmail = emailCheck;
            console.log("validId, PW, Email:  " + idCheck + " " + pwCheck + " " + emailCheck);

            _this.save(validId, validPw, validEmail);
        });
    },

    // 멤버 저장
    save: function (idCheck, pwCheck, emailCheck) {


        if (!idCheck) {
            alert("아이디 중복검사를 해주세요");
            return;
        }

        if (!pwCheck) {
            alert("비밀번호를 작성해주세요")
            return;
        }

        if (!emailCheck) {
            alert("이메일을 작성해주세요")
            return;
        }

        var data = {
            identifier: $('#userId').val(),
            password: $('#userPW1').val(),
            email: $('#userEmail').val()
        };


        if (idCheck && pwCheck && emailCheck) {
            $.ajax({
                type: 'post',
                url: 'api/member/save',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                console.log(data)
                alert("환영합니다");
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        } // end if
    },
}
index.init();