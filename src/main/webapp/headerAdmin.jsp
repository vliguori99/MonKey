<html>
<head>
    <link rel="stylesheet" href="css/headerAdmin.css" type="text/css">
</head>
<body>
<header class="header">
    <p><img class="header_logo_img" src="icone/logo.png" alt="logo"></p>
    <h2 class="header_logo_text">MonKEY</h2>
</header>
<section class="header_menu">
    <nav>
        <ul>
            <li class="header_menu_item"><a href="DisplayAdminProducts">Gestione Prodotti</a></li>
            <li class="header_menu_item"><a href="DisplayAdminUsers">Gestione Utenti</a></li>
            <li class="header_menu_item"><a href="DisplayAdminOrders">Gestione Ordini</a></li>
            <%
                if(session.getAttribute("userCode")!= null)
                {
            %>
            <li class="header_menu_item"><a href="Logout">Logout</a></li>
            <%
                }
            %>
        </ul>
    </nav>
</section>
<section class="header-menu2">
    <nav>
        <ul>
            <li class="header_menu_item"><a href="DisplayAdminProducts">Prodotti</a></li>
            <li class="header_menu_item"><a href="DisplayAdminUsers">Utenti</a></li>
            <li class="header_menu_item"><a href="DisplayAdminOrders">Ordini</a></li>
            <%
                if(session.getAttribute("userCode")!= null)
                {
            %>
            <li class="header_menu_item"><a href="Logout">Logout</a></li>
            <%
                }
            %>
        </ul>
    </nav>
</section>
</body>
</html>