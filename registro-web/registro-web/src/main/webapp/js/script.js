document.getElementById("registroForm").addEventListener("submit", function (e) {
    const nombreCompleto = document.querySelector('input[name="nombreCompleto"]').value.trim();
    const correo = document.querySelector('input[name="correo"]').value.trim();
    const contraseña = document.querySelector('input[name="contraseña"]').value;
    const confirmarContraseña = document.querySelector('input[name="confirmarContraseña"]').value;

    let errores = [];
    
    document.querySelectorAll(".error-message").forEach(function (errorDiv) {
        errorDiv.textContent = "";
    });

    if (!nombreCompleto || !correo) {
        errores.push({campo: "nombreCompleto", mensaje: "Por favor, complete todos los campos obligatorios."});
        errores.push({campo: "correo", mensaje: "Por favor, complete todos los campos obligatorios."});
    }

    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (correo && !emailRegex.test(correo)) {
        errores.push({campo: "correo", mensaje: "Por favor, ingrese un correo electrónico válido."});
    }

    if (contraseña !== confirmarContraseña) {
        errores.push({campo: "confirmarContraseña", mensaje: "Las contraseñas no coinciden."});
    }

    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (contraseña && !passwordRegex.test(contraseña)) {
        errores.push({campo: "contraseña", mensaje: "La contraseña debe tener al menos 8 caracteres, y contener letras y números."});
    }

    if (errores.length > 0) {
        e.preventDefault();
        
        errores.forEach(function (error) {
            document.getElementById("error" + error.campo.charAt(0).toUpperCase() + error.campo.slice(1)).textContent = error.mensaje;
        });
    }
});

