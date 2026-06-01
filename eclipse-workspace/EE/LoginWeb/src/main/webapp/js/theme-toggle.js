// Theme toggle: toggles 'ms-light' class on <html> and persists selection in localStorage
(function(){
  var key = 'ms_theme';
  function setTheme(light){
    if(light) document.documentElement.classList.add('ms-light'); else document.documentElement.classList.remove('ms-light');
    try{ localStorage.setItem(key, light ? 'light' : 'dark'); }catch(e){}
    var btn = document.getElementById('theme-toggle'); if(btn) btn.textContent = light ? '🌞' : '🌙';
  }
  // Initialize from storage or prefer dark
  try{ var stored = localStorage.getItem(key); setTheme(stored === 'light'); }catch(e){ setTheme(false); }

  window.toggleTheme = function(){ var isLight = document.documentElement.classList.toggle('ms-light'); setTheme(isLight); };

  document.addEventListener('DOMContentLoaded', function(){
    var btn = document.getElementById('theme-toggle');
    if(btn) btn.addEventListener('click', function(e){ e.preventDefault(); window.toggleTheme(); });
  });
})();
