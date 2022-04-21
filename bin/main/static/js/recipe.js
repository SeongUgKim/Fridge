const input = document.querySelector(".search-control");
const searchBtn = document.getElementById('search-btn');
const mealList = document.getElementById('meal');
const mealDetailsContent = document.querySelector('.meal-details-content');
const recipeCloseBtn = document.getElementById('recipe-close-btn');

// event listeners
searchBtn.addEventListener('click', getMealList);
mealList.addEventListener('click', getMealRecipe);
recipeCloseBtn.addEventListener('click', () => {
    mealDetailsContent.parentElement.classList.remove('showRecipe');
});


// get meal list that matches with the ingredients
function getMealList(){
    // LOCAL VARIABLES
    // ingredientsArray: split and store all ingredients we have (from first fetch in the future)
    const ingredientsArray = input.value.split(",");
    // string form to be used for spoonacularfetch
    let ingredientsString = "";

    if (ingredientsArray.length == 0) return;

    if (ingredientsArray.length == 1) {
        ingredientsString += ingredientsArray[0];
    } else {
        for (let i = 0; i < ingredientsArray.length; i++) {
            // first element
            if (i == 0) {
                ingredientsString += ingredientsArray[i] + ",";
                continue;
            }

            // second element to last to second element need plus sign too
            if (i < ingredientsArray.length - 1) {
                ingredientsString += "+" + ingredientsArray[i] + ",";
                continue;
            }

            // last element
            ingredientsString += ingredientsArray[i];
        }
    }

    fetch(`https://api.spoonacular.com/recipes/findByIngredients?ingredients=${ingredientsString}&apiKey=bd1b2139ffbd41038bb21238cbb4151e`)
    .then(response => response.json())
    .then((data) => {
        let neededIngredientsHtml = "";
        let possessedIngredientsHtml = "";
        let html = "";

        data.forEach(recipe => {
            //clear all strings
            neededIngredientsHtml = "";
            possessedIngredientsHtml = "";

            // get all needed ingredients and create list elements
            recipe.missedIngredients.forEach(ingredient => {
                neededIngredientsHtml +=
                    `<li>${ingredient.name}</li>`;
            });

            // get all possessed ingredients and create list elements
            recipe.usedIngredients.forEach(ingredient => {
               possessedIngredientsHtml +=
               `<li>${ingredient.name}<li>`;
            });
            html += `
                <div class = "meal-item">
                    <div class = "meal-img">
                        <img src = "${recipe.image}" alt = "food">
                    </div>
                    <div class = "meal-name">
                        <h3>${recipe.title}</h3>
                        <p>You have to get</p>
                        <ul class="ingredients-needed">
                            ${neededIngredientsHtml}
                        </ul>
                        <p>You already have these</p>
                        <ul class="ingredients-possessed">
                            ${possessedIngredientsHtml}
                        </ul>           
                        <a href = "#" class = "recipe-btn">Get Recipe</a>
                    </div>
                </div>
            `;
        });


        mealList.innerHTML = html;
    });
}

/*
//get recipe of the meal
function getMealRecipe(e){
    e.preventDefault();
    if(e.target.classList.contains('recipe-btn')){
        let mealItem = e.target.parentElement.parentElement;
        fetch(`https://www.themealdb.com/api/json/v1/1/lookup.php?i=${mealItem.dataset.id}`)
        .then(response => response.json())
        .then(data => mealRecipeModal(data.meals));
    }
}

// create a modal
function mealRecipeModal(meal){
    console.log(meal);
    meal = meal[0];
    let html = `
        <h2 class = "recipe-title">${meal.strMeal}</h2>
        <p class = "recipe-category">${meal.strCategory}</p>
        <div class = "recipe-instruct">
            <h3>Instructions:</h3>
            <p>${meal.strInstructions}</p>
        </div>
        <div class = "recipe-meal-img">
            <img src = "${meal.strMealThumb}" alt = "">
        </div>
        <div class = "recipe-link">
            <a href = "${meal.strYoutube}" target = "_blank">Watch Video</a>
        </div>
    `;
    mealDetailsContent.innerHTML = html;
    mealDetailsContent.parentElement.classList.add('showRecipe');
}

 */

