<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inventory</title>

    <style type="text/css">
        body {
            text-align: center;
        }

        table {
            margin-left: 15%;
            min-width: 70%;
            border: 1px solid #CCC;
            border-collapse: collapse;
        }

        table tr {
            line-height: 30px;
        }

        table tr th {
            background: whitesmoke;
            color: black;
        }

        table tr td {
            border: 1px solid #CCC;
            margin: 5px;
        }

        input[type=submit] {
            background: rgba(42, 49, 26, 0.22);
            padding: 5px;
            margin: 5px;
            color: #FFF;
        }

        .btn {
            horizontal-align: middle;
            display: block;
            margin: auto;
        }

        a:link, a:visited {
            color: black;
        }

        label {
            margin-left: 10px;
        }
    </style>

    <script type="text/javascript">
        function validate() {
            var qty = document.getElementById("qty");

            var shippedAfter = document.getElementById("shipped_after");
            var shippedBefore = document.getElementById("shipped_before");
            var receiveAfter = document.getElementById("receive_after");
            var receiveBefore = document.getElementById("receive_before");

            var pattern = /([А-Я]|[а-я])[а-я]{2}\s\d{2},\s\d{4}/;

            var valid = true;

            if ((qty.value != "" && qty.value < 0) || isNaN(qty.value)) {
                alert("Only positive numbers are permitted in Qty field");
                valid = false;
            }

            if ((shippedAfter.value != "" && !pattern.test(shippedAfter.value)) ||
                (shippedBefore.value != "" && !pattern.test(shippedBefore.value)) ||
                (receiveAfter.value != "" && !pattern.test(receiveAfter.value)) ||
                (receiveBefore.value != "" && !pattern.test(receiveBefore.value))) {

                alert("Wrong date format! Permitted format: мес дд, гггг");
                valid = false;
            }


            return valid;
        };
    </script>
</head>
<body>
<h1>Filter</h1>
<form onsubmit="return validate();">
    <table>
        <tr>
            <td>Part Name</td>
            <td><input type="text" name="partname"></td>
        </tr>
        <tr>
            <td>Part Number</td>
            <td><input type="text" name="partnumber"></td>
        </tr>
        <tr>
            <td>Vendor</td>
            <td><input type="text" name="vendor"></td>
        </tr>
        <tr>
            <td>Qty</td>
            <td><input id="qty" type="text" name="qty"></td>
        </tr>
        <tr>
            <td>Shipped</td>
            <td><label for="shipped_after">after</label>&nbsp;&nbsp;<input id="shipped_after" type="text"
                                                                           name="shipped_after">
                <label for="shipped_before">before</label>&nbsp;&nbsp;<input id="shipped_before" type="text"
                                                                             name="shipped_before">
            </td>
        </tr>
        <tr>
            <td>Received</td>
            <td><label for="receive_after">after</label>&nbsp;&nbsp;<input id="receive_after" type="text"
                                                                            name="receive_after">
                <label for="receive_before">before</label>&nbsp;&nbsp;<input id="receive_before" type="text"
                                                                              name="receive_before">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button class="btn">Filter</button>
            </td>
        </tr>
    </table>
</form>
<br>
<h1>Parts List</h1>

<display:table name="partsList" requestURI="">
    <display:column property="partName" sortable="true"/>
    <display:column property="partNumber" sortable="true"/>
    <display:column property="vendor" sortable="true"/>
    <display:column property="qty" sortable="true"/>
    <display:column property="shipped" format="{0,date,MMM dd, yyyy}" sortable="true"/>
    <display:column property="receive" format="{0,date,MMM dd, yyyy}" sortable="true"/>
</display:table>
</body>
</html>
