# Script de Inicio Rápido - Examen Final
# Ejecuta los componentes en el orden correcto

Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "  EXAMEN FINAL - INICIO RAPIDO" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

# Paso 1: Compilar e instalar Componente C
Write-Host "[1/3] Compilando e instalando Componente C..." -ForegroundColor Yellow
Set-Location -Path "Componente_C"
mvn clean install -DskipTests
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Componente C instalado exitosamente" -ForegroundColor Green
} else {
    Write-Host "✗ Error al instalar Componente C" -ForegroundColor Red
    exit 1
}
Set-Location -Path ".."
Write-Host ""

# Paso 2: Compilar Componente A
Write-Host "[2/3] Compilando Componente A..." -ForegroundColor Yellow
Set-Location -Path "Componente_A"
mvn clean package -DskipTests
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Componente A compilado exitosamente" -ForegroundColor Green
} else {
    Write-Host "✗ Error al compilar Componente A" -ForegroundColor Red
    exit 1
}
Set-Location -Path ".."
Write-Host ""

# Paso 3: Compilar Componente B
Write-Host "[3/3] Compilando Componente B..." -ForegroundColor Yellow
Set-Location -Path "Componente_B"
mvn clean package -DskipTests
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Componente B compilado exitosamente" -ForegroundColor Green
} else {
    Write-Host "✗ Error al compilar Componente B" -ForegroundColor Red
    exit 1
}
Set-Location -Path ".."
Write-Host ""

Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "  COMPILACION COMPLETADA" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Para iniciar los componentes, ejecuta en terminales separadas:" -ForegroundColor White
Write-Host ""
Write-Host "Terminal 1 - Componente A (Puerto 8081):" -ForegroundColor Yellow
Write-Host "  cd Componente_A" -ForegroundColor White
Write-Host "  mvn spring-boot:run" -ForegroundColor White
Write-Host ""
Write-Host "Terminal 2 - Componente B (Puerto 8082):" -ForegroundColor Yellow
Write-Host "  cd Componente_B" -ForegroundColor White
Write-Host "  mvn spring-boot:run" -ForegroundColor White
Write-Host ""
Write-Host "Documentacion de APIs:" -ForegroundColor Yellow
Write-Host "  Componente A: http://localhost:8081/swagger-ui.html" -ForegroundColor Cyan
Write-Host "  Componente B: http://localhost:8082/swagger-ui.html" -ForegroundColor Cyan
Write-Host ""
