<div class="form-inline">
    <input class="form-control" type="text" placeholder="Search" id="search-inp">
    <script>
        var inp = $('#serch-inp');

        inp.keydown(function (ev) {
            console.dir(ev);
            console.dir(ev.key);
        });

    </script>
</div>
