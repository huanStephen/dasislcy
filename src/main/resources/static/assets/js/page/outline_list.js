(function() {
    var model = new Model({
        el: 'body',
        init: {
            open: true
        },
        remote: {
            initLoad: {
                path: 'outline/getOutlines',
                params: {
                    subjectId: 1
                }
            },
            addParent: {
                path: 'outline/addOutline',
                method: 'post',
                params: {

                }
            }
        },
        events: {
            addParent: function(event) {
                console.log(this.parentName.value());
                console.log(this.parentDescription.value());
            }
        },
        include: {
            elements: {
                '#parentName': 'parentName',
                '#parentDescription': 'parentDescription'
            },

            initRenderFilter: function(fieldName, value, $el, idx, obj) {
                return value;
            }
        }
    });
})();