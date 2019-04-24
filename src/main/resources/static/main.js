(function () {

    function removeRoleElement(ev) {
        ev.preventDefault();
        this.parentElement.parentElement.remove();

    }

    window.removeRoleElement = removeRoleElement;

    function initRoleSearch(url, searchInput, results, searchList) {

        searchInput.keypress(function () {
            // if (search.val().length>0)
            searchRequest(url, searchInput.val(), appendResults);
        });


        function listContains(id) {
            console.log('id ' + id);
            var arr = results[0].children;
            if (arr) return false;
            for (var i = 0; i < arr.length; i++) {
                if (JSON.parse(decodeURIComponent(arr[i].children[0].value)) == id) return true;
            }
            return false;
        }

        function createElement(item) {
            return $('<div>', {class: 'form-row my-1'})
                .append($('<input>', {
                    name: 'sroles',
                    type: "hidden",
                    value: encodeURIComponent(JSON.stringify({itemId: item.id, description: ''}))
                }))
                .append($('<div>', {class: 'form-group col'})
                    .append($('<input>', {
                        type: "text",
                        class: "form-control form-control-plaintext",
                        readonly: true,
                        value: item.name
                    })))
                .append($('<div>', {class: 'form-group col'})
                    .append($('<input>', {
                            type: "text",
                            class: "form-control",
                            placeholder: "Role..."
                        }).keyup(function () {
                            var storage = this.parentElement.parentElement.firstChild;
                            var o = JSON.parse(decodeURIComponent(storage.value));
                            o.description = this.value;
                            storage.value = encodeURIComponent(JSON.stringify(o));
                            console.log(this);
                        })
                    ))
                .append($('<div>', {class: 'form-group col-auto'})
                    .append($('<button>', {
                        class: "btn btn-outline-danger"
                    }).text('x').click(removeRoleElement)));
        }

        function createElementSm(item) {
            return $('<button>', {class: "list-group-item"})
                .attr("item", JSON.stringify(item))
                .text(item.name)
                .click(choose);
        }

        function choose(ev) {
            ev.preventDefault();
            var item = JSON.parse(this.getAttribute('item'));
            console.log('item ' + item);
            console.dir(item);
            if (!listContains(item.id))
                results.append(createElement(item));
            this.parentElement.innerText = '';

        }


        function searchRequest(url, str, cb) {
            console.log("Start uploading films info");
            fetch(url + "?str=" + encodeURIComponent(str))
                .then(function (value) {
                    return value.json();
                })
                .then(function (page) {
                    cb(page);
                })
                .catch(function (reason) {
                    console.log(reason)
                });
        }

        function appendResults(page) {
            searchList.text("");
            for (var i = 0; i < 5 && i < page.content.length; i++) {
                var item = createElementSm(page.content[i]);
                searchList.append(item);
            }
        }

    }

    window.initRoleSearch = initRoleSearch;

    window.validateFileSize = function (fileInp, mb) {
        var fileSize = fileInp.files[0].size / 1024 / 1024; // in MB
        if (fileSize > mb) {
            alert('File size exceeds ' + mb + ' MB');
            $(fileInp).val('');
        }
    };

    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);

    window.deleteComment = function (id, token) {
        console.log("click");
        fetch("/rest/topic/" + id,
            {method: 'DELETE', headers: {_csrf: token}})
            .then(function (value) {
                if (value.status === 200) {
                    document.getElementById("comment" + id).remove();
                }
            })
            .catch(function (reason) {
                console.log(reason);
            });

    };

    /**
     *
     * @param topicId
     * @param {fetchCallback} cb
     */
    function getComments(topicId, cb) {
        fetch('/rest/topic/' + topicId)
            .then(function (value) {
                return value.json();
            })
            .then(function (value) {
                cb(value);
            })
            .catch(function (reason) {
                console.log(reason);
            });
    }

    /**
     * @callback fetchCallback
     * @param {Object} value
     */

    /**
     *
     * @param topicId
     * @param text
     * @param cb
     */
    function postComment(topicId, text, cb) {
        fetch('/rest/topic/' + topicId, {
            headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
            method: "POST",
            body: JSON.stringify({text: text})
        })
            .then(function (value) {
                cb(value.status === 200);
            })
            .catch(function (reason) {
                console.log(reason);
            });
    }

    window.getChats = function (cb) {
        fetch('/rest/im/',{
            credentials: "same-origin"
        })
            .then(function (value) {
                return value.json();
            })
            .then(function (value) {
                cb(value);
            })
            .catch(function (reason) {
                console.log(reason);
            });
    };

    window.openChat = function (id, token, cb) {
        console.log("id="+encodeURIComponent(id));
        fetch('/rest/im/open', {
            method: 'POST',
            credentials: "same-origin",
            headers: {
                // 'Accept': 'application/json',
                // 'Content-Type': 'application/json',
                'Content-Type':'application/x-www-form-urlencoded',
                'X-CSRF-TOKEN': token
            },
            body: "id="+encodeURIComponent(id)
        })
            .then(function (value) {
                cb(value.status === 200);
            })
            .catch(function (reason) {
                console.log(reason);
            });
    };

    window.getMessages = function (id, cb, page, size) {
        fetch('/rest/im/' + id, {
            headers: {
                page: page ? page : 0,
                size: size ? size : 10
            }
        })
            .then(function (value) {
                return value.json();
            })
            .then(function (value) {
                cb(value);
            })
            .catch(function (reason) {
                console.log(reason);
            });
    };

    // fetch('https://ru.wikipedia.org/w/api.php?action=opensearch&search='
    //     +encodeURIComponent('fight club'),{})


})();