<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${status}</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/main.css" type="text/css">
    <script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script rel="script" type="application/javascript" src="/static/main.js"></script>
</head>

<body>

<main>
    <h1 class="text-center" style="font-size: 16rem;">${status}</h1>
    <h1 class="text-center mb-3">${message}</h1>
    <div class="d-flex flex-row justify-content-center">
        <a class="btn btn-outline-dark btn-lg m-1" href="/">Go Home</a>
        <a class="btn btn-outline-info btn-lg m-1" onclick="goBack()">Go Back</a>
        <script>
            function goBack() {
                history.back();
            }
        </script>
    </div>
</main>

</body>
</html>