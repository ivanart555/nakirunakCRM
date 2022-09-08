$().ready(function () {
    $('form').each(function () {
        $(this).validate({
            rules: {
                name: {
                    required: true,
                    pattern: "(^[А-Я][а-яА-я][а-яА-я\\s]{0,48}$)",
                }
            },
            messages: {
                name: {
                    required: "Увядзіце, калі ласка, назву краіны.",
                    pattern: "Назва краіны павінна складацца з кірылічных літар і мець даўжыню не больш за 50 сімвалаў.",
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
