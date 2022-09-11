$().ready(function () {
    $('form').each(function () {
        $(this).validate(
            {
                rules: {
                    name: {
                        required: true,
                        maxlength: 30,
                    },
                    lastName: {
                        maxlength: 40,
                    },
                    patronymic: {
                        maxlength: 30,
                    },
                    phoneNumber: {
                        required: true,
                        pattern: "^\\+375\\s\\(\\d\\d\\)\\s\\d\\d\\d\\-\\d\\d\\-\\d\\d$",
                    },
                    email: {
                        pattern: "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
                    }
                },
                messages: {
                    name: {
                        required: "Увядзіце, калі ласка, імя кліента.",
                        maxlength: "Імя павінна быць даўжынёй да 30 сімвалаў.",
                    },
                    lastName: {
                        maxlength: "Прозвішча павінна быць даўжынёй да 40 сімвалаў.",
                    },
                    patronymic: {
                        maxlength: "Імя па бацьку павінна быць даўжынёй да 30 сімвалаў.",
                    },
                    phoneNumber: {
                        required: "Увядзіце, калі ласка, нумар тэлефона.",
                        pattern: "Тэлефон павінны быць у фармаце: +375 (29) 111-11-11"
                    },
                    email: {
                        pattern: "Email павінны быць у фармаце: abc@gmail.com"
                    }
                },

                submitHandler: function (
                    form) {
                    form
                        .submit();
                }

            });

    });
});