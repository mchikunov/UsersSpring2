<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<FName></FName>
</head>
<body>
	<center>
		<h1>Users Citadel</h1>

	</center>
    <div align="center">

        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            	</h2>
            </caption>

            <tr>
                <th>Name: </th>
                <td>
                	<input type="text" name="FName" size="45"
                			value="WELCOME DEAR <c:out value='${pageContext["request"].userPrincipal.principal.getUsername()}' />"
                		/>
                </td>
            </tr>


			<tr>
				<th>Role: </th>
				<td>
					<input type="text" name="role" size="45"
						   value="<c:out value='${pageContext["request"].userPrincipal.principal.getAuthorities()}' />"
					/>
				</td>
			</tr>
            <tr>

            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
