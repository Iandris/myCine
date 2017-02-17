<form action="/mycine/enroll" metho="POST" class="sky-form">
    <table>
        <tr>
            <td>
                <label class="form-label">First Name: </label>
            </td>
            <td>
                <input class="form-control" type="text" id="firstname" name="firstname" />
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">Last Name: </label>
            </td>
            <td>
                <input class="form-control" type="text" id="lastname" name="lastname" />
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">Address Line 1: </label>
            </td>
            <td>
                <input class="form-control" type="text" id="address1" name="address1">
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">Address Line 2 </label>
            </td>
            <td>
                <input class="form-control" type="text" id="address2" name="address2">
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">City: </label>
            </td>
            <td>
                <input class="form-control" type="text" id="city" name="city">
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">State: </label>
            </td>
            <td>
                <!-- <input class="form-control" type="text" id="state" name="state"> -->
                <select class="form-control" id="state" name="state"> 
                    <c:forEach var="state" items="${states}"> 
                        <option value="${state.idstate}">${state.shortname}</option> 
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">Zip: </label>
            </td>
            <td>
                <input class="form-control" type="text" id="zip" name="zip">
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">Email: </label>
            </td>
            <td>
                <input class="form-control" type="text" id="email" name="email" />
            </td>
        </tr>
        <tr>
            <td>
                <label class="form-label">Phone Number: </label>
            </td>
            <td>
                <input class="form-control" type="text" id="cellnumber" name="cellnumber" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="text" id="uid" name="uid" style="display:none"/>
                <input type="submit" value="submit" />
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">

</script>