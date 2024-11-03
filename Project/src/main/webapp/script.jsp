document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/api/products')  
        .then(response => response.json())
        .then(data => {
            const productsDiv = document.getElementById('products');
            data.forEach(product => {
                const productElement = document.createElement('div');
                productElement.innerHTML = `
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <p>Price: $${product.price}</p>
                    <img src="${product.image}" alt="${product.name}" />
                `;
                productsDiv.appendChild(productElement);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
});
