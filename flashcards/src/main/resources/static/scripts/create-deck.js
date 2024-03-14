document.addEventListener("DOMContentLoaded", function () {
  document
    .getElementById("newDeckForm")
    .addEventListener("submit", function (e) {
      e.preventDefault();

      const deckName = document.getElementById("deckName").value;

      fetch("http://localhost:8080/decks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: deckName }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((deck) => {
          console.log("Deck created successfully", deck);
          window.location.href = "index.html";
        })
        .catch((error) => {
          console.error("Error creating deck:", error);
        });
    });
});
