$().ready(function () {
    $('form').each(function () {
        $(this).validate(
            {
                rules: {
                    timestamp: {
                        required: true,
                    },
                    destinationId: {
                        required: true,
                    },
                    orderStatusId: {
                        required: true,
                    },
                    customerId: {
                        required: true,
                    },
                    comment: {
                        maxlength: 1000,
                    },
                    customerComment: {
                        maxlength: 1000,
                    }
                },
                messages: {
                    timestamp: {
                        required: "Увядзіце, калі ласка, дату і час.",
                    },
                    destinationId: {
                        required: "Выберыце, калі ласка, накірунак.",
                    },
                    orderStatusId: {
                        required: "Выберыце, калі ласка, статус.",
                    },
                    customerId: {
                        required: "Выберыце, калі ласка, кліента.",
                    },
                    comment: {
                        maxlength: "Каментар павінны быць даўжынёй да 1000 сімвалаў"
                    },
                    customerComment: {
                        pattern: "Каментар кліента павінны быць даўжынёй да 1000 сімвалаў"
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