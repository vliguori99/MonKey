<html>
<head>
    <link rel="stylesheet" href="css/header.css" type="text/css">
</head>
<body>
<header class="header">
    <a href="index.jsp"><img src="icone/logo.png" alt="logo" class="header_logo_img"></a>
    <h2 class="header_logo_text">MonKEY</h2>
    <span class="ricerca">
		<form class="search-form" action="DisplaySearch" method="POST" name="Search">
			<input class="input_search" type="search" name="s" placeholder="Ricerca">
			<input type="image" src="icone/ricerca.png" alt="Submit" class="icon">
		</form>
		</span>
</header>
<section class="header_menu">
    <nav>
        <ul>
            <li class="header_menu_item"><a href="DisplayLogin">Account</a></li>
            <li class="header_menu_item"><a href="assistenza.jsp">Assistenza</a></li>
            <li class="header_menu_item"><a href="DisplayCart">Carrello</a></li>
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