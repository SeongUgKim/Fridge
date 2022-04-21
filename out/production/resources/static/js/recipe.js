const input = document.querySelector(".search-control");
const searchBtn = document.getElementById('search-btn');
const mealList = document.getElementById('meal');
const mealDetailsContent = document.querySelector('.meal-details-content');
const recipeCloseBtn = document.getElementById('recipe-close-btn');

// event listeners
searchBtn.addEventListener('click', getMealList);
document.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        getMealList();
    }
});
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

    fetch(`https://api.spoonacular.com/recipes/findByIngredients?ingredients=${ingredientsString}&apiKey=d919852294154b1f88520b2c0621f855`)
    .then(response => response.json())
    .then(async (data) => {
        let neededIngredientsHtml = "";
        let possessedIngredientsHtml = "";
        let recipeUrl ="";
        let recipeId = 0;
        let html = "";

        for(let i = 0; i<6; i++) {
            if(data[i]) {
                let recipe = data[i];
                //clear all strings
                neededIngredientsHtml = "";
                possessedIngredientsHtml = "";
                recipeUrl = "";
                recipeId = recipe.id;

                let aboutRecipeData = await fetch(
                    `https://api.spoonacular.com/recipes/${recipeId}/information?includeNutrition=false&apiKey=d919852294154b1f88520b2c0621f855`
                );

                // GET JSON
                aboutRecipeData = await aboutRecipeData.json();
                // EXTRACT URL FROM THAT JSON
                recipeUrl = aboutRecipeData.spoonacularSourceUrl;

                // get all needed ingredients and create list elements
                recipe.missedIngredients.forEach(ingredient => {
                    neededIngredientsHtml +=
                        `<li>${ingredient.name}</li>`;
                });

                // get all possessed ingredients and create list elements
                recipe.usedIngredients.forEach(ingredient => {
                    possessedIngredientsHtml +=
                        `<li>${ingredient.name}</li>`;
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
                        <a href = "${recipeUrl}" target ="_blank" class = "recipe-btn">Get Recipe</a>
                    </div>
                </div>
            `;
            }
        }
        mealList.innerHTML = html;
    });
}



window.onload = function() {
    let value = "";
    let editedValue = "";
    fetch("http://localhost:8080/api/items")
        .then(response => response.json())
        .then((data) => {
            data.forEach(item => {
                value += item.itemName + ",";
            })
            editedValue = value.slice(0,-1);
// input from inventory

            const ingredientsArray = editedValue.split(",");
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

            fetch(`https://api.spoonacular.com/recipes/findByIngredients?ingredients=${ingredientsString}&apiKey=d919852294154b1f88520b2c0621f855`)
                .then(response => response.json())
                .then(async (data) => {
                    let neededIngredientsHtml = "";
                    let possessedIngredientsHtml = "";
                    let recipeUrl ="";
                    let recipeId = 0;
                    let html = "";

                    for(let i = 0; i<6; i++) {
                        if(data[i]) {
                            let recipe = data[i];
                            //clear all strings
                            neededIngredientsHtml = "";
                            possessedIngredientsHtml = "";
                            recipeUrl = "";
                            recipeId = recipe.id;

                            let aboutRecipeData = await fetch(
                                `https://api.spoonacular.com/recipes/${recipeId}/information?includeNutrition=false&apiKey=d919852294154b1f88520b2c0621f855`
                            );

                            // GET JSON
                            aboutRecipeData = await aboutRecipeData.json();
                            // EXTRACT URL FROM THAT JSON
                            recipeUrl = aboutRecipeData.spoonacularSourceUrl;
                            console.log(recipe);
                            console.log("====================================");
                            // get all needed ingredients and create list elements
                            recipe.missedIngredients.forEach(ingredient => {
                                neededIngredientsHtml +=
                                    `<li>${ingredient.name}</li>`;
                            });

                            // get all possessed ingredients and create list elements
                            recipe.usedIngredients.forEach(ingredient => {
                                possessedIngredientsHtml +=
                                    `<li class = "possessed">${ingredient.name}</li>`;
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
                        <a href = "${recipeUrl}" target ="_blank" class = "recipe-btn">Get Recipe</a>
                    </div>
                </div>
            `;
                        }
                    }
                    mealList.innerHTML = html;
                });


        });
};





