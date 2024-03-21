function openLink(evt, linkName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("myLink");
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
    }
    document.getElementById(linkName).style.display = "block";
    evt.currentTarget.className += " w3-red";
  }
  
  // Click on the first tablink on load
  document.getElementsByClassName("tablink")[0].click();


  // dropdown page initial

  var passengers = {
    adult: 1,
    child: 0
};

function togglePassengerDropdown(section) {
  var dropdownId = "passengerDropdown" + section;
  var dropdown = document.getElementById(dropdownId);
  if (dropdown.className.indexOf("w3-show") == -1) { 
      dropdown.className += " w3-show";
  } else {
      dropdown.className = dropdown.className.replace(" w3-show", "");
  }
}


function addPassenger(type) {
    if (type === 'adult') {
        passengers.adult++;
    } else if (type === 'child') {
        passengers.child++;
    }
    
    updatePassengerCount();
}

function removePassenger(type) {
    if (type === 'adult') {
        if (passengers.adult > 1) {
            passengers.adult--;
        }
    } else if (type === 'child') {
        if (passengers.child > 0) {
            passengers.child--;
        }
    }
    
    updatePassengerCount();
}

function updatePassengerCount() {
    var adultCountElement = document.getElementById("adultCount");
    var childCountElement = document.getElementById("childCount");

    adultCountElement.textContent = passengers.adult;
    childCountElement.textContent = passengers.child;

    updatePassengerButton();
}

function updatePassengerButton() {
    var button = document.getElementById("passengers");
    button.innerHTML = passengers.adult + " Adult" + (passengers.adult > 1 ? "s" : "") + (passengers.child >= 1 ? ", " + passengers.child + " Children" + (passengers.child > 1 ? "s" : "") : "");
}
