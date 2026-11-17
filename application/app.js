const $=id=>document.getElementById(id);
let chosenBus=null, selectedSeat=null, passenger=null;
const bookedSeats=new Set(["A2","B3"]);
const today=new Date(); $("journeyDate").value=new Date(today.getTime()-today.getTimezoneOffset()*60000).toISOString().slice(0,10);
function show(id){$(id).hidden=false}
function hide(id){$(id).hidden=true}
$("searchBtn").addEventListener("click",()=>{
 const from=$("source").value.trim();
 const to=$("destination").value.trim();
 const date=$("journeyDate").value;

 $("searchError").textContent="";

 if(!from||!to||!date){
  $("searchError").textContent="Please enter source, destination and journey date.";
  return;
 }

 if(from.toLowerCase()===to.toLowerCase()){
  $("searchError").textContent="Source and destination must be different.";
  return;
 }

 const now=new Date();
 const todayLocal=[
  now.getFullYear(),
  String(now.getMonth()+1).padStart(2,"0"),
  String(now.getDate()).padStart(2,"0")
 ].join("-");

 if(date<todayLocal){
  $("searchError").textContent="Journey date cannot be in the past.";
  return;
 }

 $("busList").innerHTML="";

 ["Swift Express","City Traveller"].forEach((name,i)=>{
  const d=document.createElement("div");
  d.className="bus";
  d.innerHTML=`<div><strong>${name}</strong><p>${i?"09:30 – 15:00":"07:00 – 12:30"} · ${from} → ${to}</p></div><div>₹${i?650:550} <button data-bus="${name}">Select bus</button></div>`;
  $("busList").appendChild(d);
 });

 show("results");
 hide("seats");
 hide("passenger");
 hide("payment");
 hide("confirmation");
});
$("busList").addEventListener("click",e=>{if(e.target.dataset.bus){chosenBus=e.target.dataset.bus;selectedSeat=null;renderSeats();show("seats")}});
function renderSeats(){$("seatMap").innerHTML="";["A1","A2","A3","A4","B1","B2","B3","B4"].forEach(seat=>{const b=document.createElement("button");b.textContent=seat;b.className="seat"+(bookedSeats.has(seat)?" booked":"");b.disabled=bookedSeats.has(seat);b.addEventListener("click",()=>{selectedSeat=seat;document.querySelectorAll(".seat").forEach(x=>x.classList.remove("selected"));b.classList.add("selected");$("seatError").textContent=""});$("seatMap").appendChild(b)})}
$("continueBtn").addEventListener("click",()=>{if(!selectedSeat){$("seatError").textContent="Please select an available seat.";return}show("passenger")});
$("payBtn").addEventListener("click",()=>{const name=$("passengerName").value.trim(),age=Number($("age").value),mobile=$("mobile").value.trim();$("passengerError").textContent="";
if(!name||!$("age").value||!mobile){$("passengerError").textContent="All passenger fields are required.";return}
if(!Number.isInteger(age)||age<5||age>100){$("passengerError").textContent="Passenger age must be between 5 and 100.";return}
if(!/^[0-9]{10}$/.test(mobile)){$("passengerError").textContent="Enter a valid 10-digit mobile number.";return}
passenger={name,age,mobile};show("payment")});
$("confirmBtn").addEventListener("click",()=>{if($("paymentOutcome").value==="fail"){$("paymentError").textContent="Payment failed. Booking was not confirmed.";return}
bookedSeats.add(selectedSeat);const booking={id:"SB"+Date.now().toString().slice(-7),bus:chosenBus,seat:selectedSeat,passenger:passenger.name,status:"CONFIRMED"};
const all=JSON.parse(localStorage.getItem("swiftbusBookings")||"[]");all.push(booking);localStorage.setItem("swiftbusBookings",JSON.stringify(all));
$("ticketText").textContent=`Booking ID: ${booking.id} | Bus: ${booking.bus} | Seat: ${booking.seat} | Passenger: ${booking.passenger} | Status: CONFIRMED`;
$("paymentError").textContent="";renderBookings();show("confirmation")});
function renderBookings(){const all=JSON.parse(localStorage.getItem("swiftbusBookings")||"[]");$("bookingList").innerHTML=all.length?all.map(b=>`<p>${b.id} — ${b.bus} — Seat ${b.seat} — ${b.status} ${b.status==="CONFIRMED"?`<button data-cancel="${b.id}">Cancel</button>`:""}</p>`).join(""):"No bookings yet."}
$("bookingList").addEventListener("click",e=>{if(e.target.dataset.cancel){const all=JSON.parse(localStorage.getItem("swiftbusBookings")||"[]");const b=all.find(x=>x.id===e.target.dataset.cancel);if(b)b.status="CANCELLED";localStorage.setItem("swiftbusBookings",JSON.stringify(all));renderBookings()}});
$("newSearchBtn").addEventListener("click",()=>{hide("confirmation");hide("passenger");hide("payment");hide("seats");});
renderBookings();