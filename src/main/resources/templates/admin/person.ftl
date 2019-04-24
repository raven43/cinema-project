<#import "common.ftl" as common>

<@common.page "Person Edit" 1>
    <#if message??>
    <h1 class="alert alert-success my-2">${message}</h1>
    </#if>

    <form class="need-validation" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label for="name-inp">Person name</label>
            <input type="text" class="form-control" id="name-inp"
                   name="name" placeholder="Edit name..." required <#if item??>value="${item.name}"</#if> >

            <div class="valid-feedback"> Looks good!</div>
            <div class="invalid-feedback"> Go to hell!</div>
        </div>

        <div class="form-group">
            <label for="birth">Birth</label>
            <input type="date" class="form-control" id="birth" name="birth" required
                   <#if item??&&item.birth??>value="${item.birth?date?string("yyyy-MM-dd")}"</#if>
            >
        </div>

        <div class="form-group">
            <label for="search">Roles</label>
            <input type="text" class="form-control" id="search" name="search" placeholder="Search...">

            <div class="position-absolute" style="z-index: 989">
                <ul id="search-list" class="list-group">

                </ul>
            </div>

            <div id="list">
                <#if item??>
                    <#list item.roles as role>
                        <div class="form-row my-1">
                            <input name="roles" type="hidden"
                                   value="${role.toDTOStringFilm()}">
                            <div class="form-group col">
                                <input type="text" class="form-control form-control-plaintext" readonly="readonly"
                                       value="${role.film.name}">
                            </div>
                            <div class="form-group col">
                                <input type="text" class="form-control" placeholder="Role..." value="${role.description?if_exists}">
                            </div>
                            <div class="form-group col-auto">
                                <button class="btn btn-outline-danger" onclick="removeRoleElement.call(this,event)">x</button>
                            </div>
                        </div>
                    </#list>
                </#if>

            </div>
        </div>

        <script>
            var search = $('#search');
            var list = $('#list');
            var searchList = $('#search-list');
            initRoleSearch('/rest/search/films', search, list, searchList);

        </script>


        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="fileInpAddon">Picture</span>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="fileInp" name="file"
                       aria-describedby="fileInpAddon" onchange="validateFileSize(this,2)">
                <label class="custom-file-label" for="fileInp">Choose file</label>
            </div>
        </div>

        <div class="form-group">
            <label for="txtArea">Description</label>
            <textarea class="form-control" id="txtArea" name="description" maxlength="4096" rows="6"
                      placeholder="Description"><#if item??>${item.description}</#if></textarea>
        </div>

        <div class="form-group input-group">
            <div class="btn-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <#if item??&&item.imgName??>
                <input type="hidden" name="imgName" value="${item.imgName}">
                </#if>

                <button type="submit" class="btn btn-primary">Upload</button>
            </div>
        </div>
    </form>

</@common.page>