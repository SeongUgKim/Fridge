const quotes = [
    {
        quote: "A recipe has no soul. You, as the cook, must bring soul to the recipe.",
        author: "-- Thomas Keller",
    },
    {
        quote: "A waffle is like a pancake with a syrup trap.",
        author: "-- Mitch Hedberg",
    },
    {
        quote: "In wine there is wisdom, in beer there is strength, in water there is bacteria.",
        author: "-- David Auerbach" ,
    },
    {
        quote: "The secret of success in life is to eat what you like and let the food fight it out inside",
        author: "-- Mark Twain",
    },
    {
        quote: "Your diet is a bank account. Good food choices are good investments",
        author: "-- Bethenny Frankel",
    },
    {
        quote: "If you cant' feed a hundred people, then just feed one",
        author: "-- Mother Teresa",
    }
];

const quote = document.querySelector(".text h3");
const author = document.querySelector(".text span");

const todaysQuote = quotes[Math.floor((Math.random()*quotes.length))];

quote.innerText = todaysQuote.quote;
author.innerText = todaysQuote.author;