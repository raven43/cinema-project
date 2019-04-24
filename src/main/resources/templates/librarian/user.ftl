<#import "common.ftl" as librarian>
<#import "../parts/common.ftl" as common>

<@common.page "Serve client "+client.username>

    <h1>Serve client ${client.username}</h1>
    <#if message??>
    <div class="alert ${message_style?if_exists} my-3">${message}</div>
    </#if>

    <#if borrowings?size!=0>
    <table class="table table-striped my-3">
        <thead>
        <tr>
            <td>Borrow date</td>
            <td>Return date</td>
            <td>Author</td>
            <td>Book</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
            <#list borrowings as b>
            <tr>
                <td>${b.borrowDate?datetime}</td>
                <td>${b.returnDate?datetime}</td>
                <td>${b.book.author.name}</td>
                <td>${b.book.name}</td>
                <td>
                    <form method="post" action="/librarian/${client.id}/delete">
                        <input type="hidden" name="borrowing_id" value="${b.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btn btn-outline-danger" type="submit">Close</button>
                    </form>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
    </#if>

<#--<@librarian.client_view client></@librarian.client_view>-->
    <div class="row my-2">
        <div class="col-auto">
            <a href="/librarian/${client.id}">
            <#if client.imgName??>
                <img src="/file/${client.imgName}" class="icon-r-lg">
            <#else >
            <img src="/file/default.png" class="icon-r-lg">
            </#if>
            </a>

        </div>
        <div class="col">
            <div class="card">
                <h3 class="card-header py-2">
                    <a href="/librarian/${client.id}">${client.username}</a>
                </h3>
                <div class="card-body w-100 py-2">
                    <form method="post">
                        <div class="form-group">
                            <label for="borrowDate">Borrow date</label>
                            <input id="borrowDate" class="form-control disabled" type="datetime-local"
                                   name="borrowDate">
                            <script>document.getElementById("borrowDate").value = new Date().toISOString().slice(0,-8);</script>
                        </div>
                        <div class="form-group">
                            <label for="returnDate">Return date</label>
                            <input id="returnDate" class="form-control" type="datetime-local"
                                   name="returnDate">
                            <script>document.getElementById("returnDate").value = new Date().toISOString().slice(0,-8);</script>
                        </div>

                        <div class="form-group dropdown">
                            <input hidden name="book_id" id="book_id">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dd-btn"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Choose book
                            </button>
                            <div class="dropdown-menu py-1" aria-labelledby="dd-btn" id="dd-menu">
                                <div class="dropdown-item px-2">
                                    <input id="book-inp" class="form-control" type="text">
                                </div>
                                <div class="dropdown-divider"></div>
                                <div id="list">
                                </div>
                            </div>
                        </div>
                        <script>
                            initSearch(
                                    "books",
                                    $('#dd-btn'),
                                    $('#book-inp'),
                                    $('#list'),
                                    $('#book_id')
                            );
                        </script>
                        <div class="form-group">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-primary" id="sbm">Open Borrowing</button>
                            <script>
                                $("#sbm").on("click",function (ev) {
                                    if ($("#book_id").val()=="")
                                        ev.preventDefault();
                                });
                            </script>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


</@common.page>