// Small helper to reveal elements with .ms-appear when they enter the viewport
(function(){
  if(!('IntersectionObserver' in window)){
    document.querySelectorAll('.ms-appear').forEach(function(el){ el.classList.add('in-view'); });
    return;
  }

  var obs = new IntersectionObserver(function(entries){
    entries.forEach(function(entry){
      if(entry.isIntersecting){ entry.target.classList.add('in-view'); obs.unobserve(entry.target); }
    });
  },{ threshold: 0.12 });

  document.querySelectorAll('.ms-appear').forEach(function(el){ obs.observe(el); });
})();
