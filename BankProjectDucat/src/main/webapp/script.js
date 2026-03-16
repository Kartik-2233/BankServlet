// Alert Helper - Keep this for consistent UI
function showAlert(elementId, message, type) {
    const alertDiv = document.getElementById(elementId);
    if (!alertDiv) return;
    
    alertDiv.innerHTML = `
        <div class="alert alert-${type}">
            <span>${type === 'success' ? '✓' : '⚠'}</span>
            <span>${message}</span>
        </div>
    `;
    
    setTimeout(() => {
        alertDiv.innerHTML = '';
    }, 5000);
}

// Quick UI Helpers for Admin/User pages
function quickAddAmount(amount) {
    const input = document.getElementById('addMoneyAmount') || document.getElementById('depositAmount');
    if(input) {
        input.value = amount;
        input.focus();
    }
}

// Redirect and session check logic can be moved to a Java Filter later,
// but for now, we use pure HTML forms to talk to Servlets.