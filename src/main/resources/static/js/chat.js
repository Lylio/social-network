(() => {
  if (!recipient) return;
  const csrf = document.querySelector('meta[name="csrf-token"]')?.content;
  const protocol = location.protocol === 'https:' ? 'wss' : 'ws';
  const client = new StompJs.Client({brokerURL: `${protocol}://${location.host}/ws`, connectHeaders: csrf ? {'X-CSRF-TOKEN': csrf} : {}});
  const box = document.getElementById('messages');
  const render = m => { const d=document.createElement('div');d.className='message'+(m.sender===currentUser?' mine':'');d.innerHTML=`<strong>${escapeHtml(m.sender)}</strong><span>${escapeHtml(m.content)}</span>`;box.appendChild(d);box.scrollTop=box.scrollHeight; };
  client.onConnect = () => client.subscribe('/user/queue/messages', frame => { const m=JSON.parse(frame.body); if ((m.sender===recipient&&m.recipient===currentUser)||(m.sender===currentUser&&m.recipient===recipient)) render(m); });
  client.activate();
  document.getElementById('chatForm').addEventListener('submit', e => {e.preventDefault();const input=document.getElementById('messageInput');if(!input.value.trim())return;client.publish({destination:'/app/chat.send',body:JSON.stringify({recipient,content:input.value})});input.value='';});
  function escapeHtml(v){const d=document.createElement('div');d.textContent=v??'';return d.innerHTML;}
})();
