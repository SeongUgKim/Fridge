const HIDDEN_CLASSNAME = "hidden";

window.onload = function() {
    const video1 = document.getElementById("video1");
    const video2 = document.getElementById("video2");
    const video3 = document.getElementById("video3");
    const video4 = document.getElementById("video4");


    video2.classList.add(HIDDEN_CLASSNAME);
    video3.classList.add(HIDDEN_CLASSNAME);
    video4.classList.add(HIDDEN_CLASSNAME);
    video1.addEventListener("ended", function(){playVideo("video1", "video2"); });
    video2.addEventListener("ended", function(){playVideo("video2", "video3"); });
    video3.addEventListener("ended", function(){playVideo("video3", "video4"); });
    video4.addEventListener("ended", function(){playVideo("video4", "video1"); });


}

function playVideo(videoID1 ,videoID2) {
    const videoElement = document.getElementById(videoID2);
    videoElement.classList.remove(HIDDEN_CLASSNAME);
    document.getElementById(videoID1).classList.add(HIDDEN_CLASSNAME);
    videoElement.play();
}