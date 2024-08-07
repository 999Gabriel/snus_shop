document.addEventListener('DOMContentLoaded', () => {
    const cartButtons = document.querySelectorAll('.product-item button');
    cartButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            const productId = event.target.getAttribute('onclick').match(/\d+/)[0];
            addToCart(productId);
        });
    });

    const contactForm = document.querySelector('form');
    contactForm.addEventListener('submit', (event) => {
        event.preventDefault();
        alert('Nachricht wurde gesendet');
    });
});

function addToCart(productId) {
    alert(`Produkt ${productId} wurde zum Warenkorb hinzugefügt`);
}