
@echo off
echo Starting all services...

start "Frontend" cmd /k "cd d:\Vmware_download\practice_class\e-commerce-platform\snack-online && python -m http.server 8090"
timeout /t 3 /nobreak >nul

start "Store Service" cmd /k "cd d:\Vmware_download\practice_class\e-commerce-platform\snack-online\store-service && mvn spring-boot:run"
timeout /t 15 /nobreak >nul

start "Trade Service" cmd /k "cd d:\Vmware_download\practice_class\e-commerce-platform\snack-online\trade-service && mvn spring-boot:run"
timeout /t 15 /nobreak >nul

start "Pay Service" cmd /k "cd d:\Vmware_download\practice_class\e-commerce-platform\snack-online\pay-service && mvn spring-boot:run"
timeout /t 15 /nobreak >nul

start "Gateway" cmd /k "cd d:\Vmware_download\practice_class\e-commerce-platform\snack-online\gateway && mvn spring-boot:run"

echo All services started.
echo Frontend: http://localhost:8090/index.html
