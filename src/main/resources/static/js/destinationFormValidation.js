$().ready(function () {
    $('form').each(function () {
        $(this).validate({
            rules: {
                name: {
                    required: true,
                    pattern: "^[А-ЯІ][а-я'ЎўІіА-я][а-я'ЎўІіА-я\\s]{0,48}$",
                }
            },
            messages: {
                name: {
                    required: "Увядзіце, калі ласка, назву краіны.",
                    pattern: "Назва краіны павінна складацца з кірылічных літар, пачанацца з вялікай літары і мець даўжыню не больш за 50 сімвалаў.",
                },

            },

            submitHandler: function (
                form) {
                form
                    .submit();
            }

        });

    });
});
