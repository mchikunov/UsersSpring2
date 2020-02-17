

$(document).ready(function () {



    $("#edit_save").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_edit()



    });

    $("#btn_save").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_add_new()



    });

    fire_admin();


});



function getdetails(obj) {
    let arr = obj.id.split('|');
    $("#name0").val(arr[0]) //id
    $("#name1").val(arr[2]) //sname
    $("#name2").val(arr[1]) //fname
    $("#name3").val(arr[3]) //age
    $("#name4").val(arr[4]) //role

    $('#edit_modal').modal('toggle')
}



function delete_(obj)
{
    //alert("sdfsf")

    var data = {};
    data["id"] = obj.id;
    data["fname"] = null;
    data["sname"] = null;
    data["age"] = null;
    data["role"] = null;



    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/delete",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
            $("#btn_save").prop("disabled", true);
           // $("#feedback").load(location.href + " #feedback");
            location.reload();
        },

        error: function (xhr, status, error) {
           // var err = eval("(" + xhr.responseText + ")");
           // alert(err.Message + status + error);
           // alert(error)
            //$("#feedback").load(location.href + " #feedback");
            location.reload();
        }

    });
}




function fire_edit()
{
    //alert("sdfsf")

    var data = {};
    data["id"] = $("#name0").val();
    data["fname"] = $("#name2").val();
    data["sname"] = $("#name1").val();
    data["age"] = $("#name3").val();
    data["role"] = $("#name4").val();

    $('#edit_modal').modal('hide');
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/update1",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
            $("#btn_save").prop("disabled", true);
            $('#edit_modal').modal('hide');
            location.reload();

        },

        error: function (xhr, status, error) {
        //    var err = eval("(" + xhr.responseText + ")");
        //    alert(err.Message + status + error);
          //  alert(error)
            location.reload();
        }

    });
}



function fire_add_new() {
    //alert("sdfsf")

    var data = {};
    data["fname"] = $("#name_id").val();
    data["sname"] = $("#surname_id").val();
    data["age"] = $("#age_id").val();
    data["role"] = $("#role_id").val();




    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/insert",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
            $("#btn_save").prop("disabled", true);
            location.reload();
        },

        error: function(xhr, status, error) {
        //    var err = eval("(" + xhr.responseText + ")");
         //   alert(err.Message + status + error);
          //  alert(error)
            location.reload();
        }

    });
}


function fire_admin() {




    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin1",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (jsn) {


           let arr = Array.from(jsn);

          //  alert(arr[1].sname);




            var json =  JSON.stringify(jsn);
            json = '{"users":'+json+'}';
            const data= JSON.parse(json);



            $('#feedback').append(
                `<tbody>${data.users.map(n =>
                    `<tr>
      <td>${n.id}</td>
      <td>${n.authorities.map(l=>l.authority).join(',')}</td>
      <td>${n.sname}</td>
      <td>${n.age}</td>
      <td>

		<button type="button"  id=${n.id}|Hidden|${n.sname}|${n.age}|${n.authorities.map(f=>f.authority).join(',')} class="btn btn-primary" onClick = "getdetails(this)">Edit</button>

		<button type="button" id=${n.id} class="btn btn-primary" onClick = "delete_(this)">Delete</button>


      </td>

    </tr>`).join('')}
</tbody>`
            );





        }//,
      //  error: function (e) {

      //      var json = "<h4>Ajax Response</h4><pre>"
       //         + e.responseText + "</pre>";
      //      $('#feedback').html(json);

       //     console.log("ERROR : ", e);


       // }
    });

}