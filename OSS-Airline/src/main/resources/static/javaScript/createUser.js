function validateForm() {
    return validatePassword() && validatePasswordMatch();
}

function validatePassword() {
    var passwordInput = document.getElementById('password');
    var passwordHelp = document.getElementById('passwordHelp');
    var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

    if (!passwordRegex.test(passwordInput.value)) {
        passwordHelp.style.color = 'red';
        passwordHelp.innerText = 'Password must be at least 8 characters long and include uppercase, lowercase, and a digit.';
        return false;
    } else {
        passwordHelp.innerText = '';
        return true;
    }
}

function validatePasswordMatch() {
    var passwordInput = document.getElementById('password');
    var confirmPasswordInput = document.getElementById('confirmPassword');
    var passwordMatchHelp = document.getElementById('passwordMatchHelp');

    if (passwordInput.value !== confirmPasswordInput.value) {
        passwordMatchHelp.innerText = 'Passwords do not match.';
        return false;
    } else {
        passwordMatchHelp.innerText = '';
        return true;
    }
}

function togglePasswordVisibility() {
    var passwordInput = document.getElementById('password');
    var visibilityButton = document.getElementById('visibilityButton');
    var visibilityIcon = document.getElementById('icon');

    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        visibilityIcon.innerText = 'visibility_off';
    } else {
        passwordInput.type = 'password';
        visibilityIcon.innerText = 'visibility';
    }
}