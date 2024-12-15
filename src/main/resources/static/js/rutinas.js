/* La siguiente función se utiliza para visualizar la imagen seleccionada en la
 * página html donde se desea "cargar" utilizando un llamado "ajax"*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah')
                    .attr('src', e.target.result)
                    .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}


/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCart(formulario) {
    // Obtener el ID del producto desde el formulario
    var idProducto = formulario.elements["idProducto"].value;

    // Construir la ruta para la solicitud
    var ruta = '/carrito/agregar/' + idProducto;

    // Mostrar un mensaje de carga mientras se procesa
    alert("Añadiendo producto al carrito: " + idProducto);

    // Llamada AJAX para enviar la solicitud al servidor
    $.get(ruta, function(response) {
        // Si la solicitud es exitosa, actualizamos el carrito
        $("#carrito").html(response);
        console.log("Producto añadido al carrito correctamente.");
    }).fail(function(xhr) {
        // Si ocurre un error, mostramos un mensaje
        alert("Error al añadir producto al carrito: " + xhr.statusText);
    });
}


