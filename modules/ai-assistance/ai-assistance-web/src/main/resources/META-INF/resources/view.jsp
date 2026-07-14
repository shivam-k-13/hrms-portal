<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<portlet:actionURL name="/ai/upload_pdf" var="uploadPDFURL" />
<portlet:resourceURL id="/ai/chat" var="chatURL" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap" />

<style type="text/css">
    .ai-wrapper {
        background: #f8fafc;
        font-family: "Inter", sans-serif;
        font-size: 15px; 
        height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* Top AI Header */
    .ai-header {
        background: linear-gradient(135deg, #0f172a 0%, #1e3a8a 100%);
        padding: 2rem 3rem;
        color: white;
        display: flex;
        justify-content: space-between;
        align-items: center;
        box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        z-index: 10;
    }
    
    /* Inline Back Button */
    .btn-back-inline {
        display: inline-flex; 
        align-items: center; 
        gap: 0.5rem;
        color: white; 
        text-decoration: none; 
        font-weight: 600; 
        font-size: 0.85rem;
        background: rgba(255, 255, 255, 0.15); 
        padding: 0.4rem 1rem;
        border-radius: 999px; 
        border: 1px solid rgba(255, 255, 255, 0.2);
        transition: 0.2s;
    }
    .btn-back-inline:hover {
        background: rgba(255, 255, 255, 0.25);
        transform: translateX(-4px);
        color: white;
    }

    .ai-header h2 { font-size: 1.8rem; font-weight: 900; margin: 0 0 0.25rem; }
    .ai-header p { font-size: 1rem; color: #cbd5e1; margin: 0; }
    .bot-icon { font-size: 3rem; animation: floatBot 3s ease-in-out infinite; }
    
    @keyframes floatBot { 0%, 100% { transform: translateY(0); } 50% { transform: translateY(-10px); } }

    /* Layout Grid */
    .ai-layout {
        display: grid;
        grid-template-columns: 320px 1fr;
        gap: 2rem;
        padding: 2rem 3rem;
        flex: 1;
        overflow: hidden; 
    }

    /* Shared Card Style */
    .ai-card {
        background: #ffffff;
        border: 1px solid #e2e8f0;
        border-radius: 20px;
        padding: 1.5rem;
        box-shadow: 0 10px 30px rgba(0, 86, 179, 0.05);
        display: flex;
        flex-direction: column;
    }

    /* Sidebar Upload */
    .sidebar { overflow-y: auto; gap: 1.5rem; }
    .upload-box {
        border: 2px dashed #cbd5e1; border-radius: 16px;
        padding: 2rem 1rem; text-align: center; cursor: pointer;
        background: #f8fafc; display: block; transition: 0.3s;
    }
    .upload-box:hover { border-color: #0088cc; background: #eaf4fc; }
    .upload-box i { font-size: 2.5rem; color: #94a3b8; margin-bottom: 1rem; display: block;}
    .upload-box strong { color: #0f172a; font-size: 1rem; display: block; margin-bottom: 0.25rem;}
    .upload-box span { color: #64748b; font-size: 0.85rem; }
    .upload-box input { display: none; }
    
    .btn-upload { background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); color: white; border: none; padding: 0.85rem; border-radius: 10px; font-weight: 700; width: 100%; margin-top: 1rem; cursor: pointer; transition: 0.2s;}
    .btn-upload:hover { box-shadow: 0 8px 15px rgba(0, 136, 204, 0.3); transform: translateY(-2px);}

    /* Indexed Status */
    .indexed-item { display: flex; align-items: center; gap: 1rem; background: #f1f5f9; padding: 1rem; border-radius: 12px; border: 1px solid #e2e8f0;}
    .indexed-item i { font-size: 1.5rem; color: #3b82f6; }

    /* Chat Panel */
    .chat-panel { border-top: 6px solid #8b5cf6; }
    .chat-body { flex: 1; overflow-y: auto; padding: 1rem; display: flex; flex-direction: column; gap: 1rem; margin-bottom: 1.5rem; background: #f8fafc; border-radius: 12px; border: 1px solid #e2e8f0; }
    
    .suggestions { display: flex; flex-wrap: wrap; gap: 0.5rem; margin-bottom: 1.5rem; }
    .chip { background: white; border: 1px solid #cbd5e1; padding: 0.5rem 1rem; border-radius: 999px; font-size: 0.9rem; font-weight: 600; color: #475569; cursor: pointer; transition: 0.2s;}
    .chip:hover { background: #eaf4fc; border-color: #0088cc; color: #0056b3; }

    /* Inputs */
    .input-group { display: flex; gap: 1rem; }
    .chat-input { flex: 1; padding: 1rem 1.5rem; border: 2px solid #e2e8f0; border-radius: 999px; font-size: 1rem; outline: none; transition: 0.2s; }
    .chat-input:focus { border-color: #0088cc; box-shadow: 0 0 0 4px rgba(0, 136, 204, 0.1); }
    .send-btn { width: 50px; height: 50px; border-radius: 50%; background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); color: white; border: none; font-size: 1.2rem; cursor: pointer; display: flex; align-items: center; justify-content: center;}

    /* Bubbles */
    .msg-row { display: flex; gap: 1rem; }
    .msg-user { flex-direction: row-reverse; }
    .avatar { width: 35px; height: 35px; border-radius: 50%; display: flex; align-items: center; justify-content: center; background: white; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    .msg-sys .avatar { background: linear-gradient(135deg, #0056b3 0%, #0088cc 100%); color: white; }
    .bubble { max-width: 80%; padding: 1rem 1.25rem; border-radius: 16px; font-size: 0.95rem; line-height: 1.5; }
    .msg-user .bubble { background: #0f172a; color: white; border-top-right-radius: 0; }
    .msg-sys .bubble { background: white; border: 1px solid #e2e8f0; border-top-left-radius: 0; box-shadow: 0 4px 15px rgba(0,0,0,0.03); }
    .lbl { font-size: 0.75rem; font-weight: 800; opacity: 0.7; margin-bottom: 0.25rem; }
</style>

<div class="ai-wrapper">
    
    <header class="ai-header">
        <div>
            <div style="margin-bottom: 1rem;">
                <a href="/web/hrms/dashboard-router" class="btn-back-inline">
                    <i class="fa-solid fa-arrow-left"></i> Dashboard
                </a>
                <span style="background: rgba(255,255,255,0.2); padding: 0.3rem 0.8rem; border-radius: 999px; font-size: 0.8rem; font-weight: 800; margin-left: 0.5rem; display: inline-block;">AI Assistant Module</span>
            </div>
            
            <h2>HRMS AI Panel</h2>
            <p>Upload policy documents and get instant answers based on company guidelines.</p>
        </div>
        <div class="bot-icon">🤖</div>
    </header>

    <div class="ai-layout">
        
        <!-- Sidebar -->
        <aside class="ai-card sidebar" style="border-top: 5px solid #0088cc;">
            <div>
                <h3 style="font-size: 1.2rem; font-weight: 900; margin-bottom: 1rem; color: #0f172a;">Upload Policy</h3>
                
                <form id="<portlet:namespace />uploadForm" action="${uploadPDFURL}" method="POST" enctype="multipart/form-data">
                    <label class="upload-box" for="<portlet:namespace />pdfFile">
                        <i class="fa-solid fa-file-pdf"></i>
                        <strong>Choose PDF Document</strong>
                        <span>Maximum file size: 10MB</span>
                        <input type="file" name="<portlet:namespace />pdfFile" id="<portlet:namespace />pdfFile" accept=".pdf" />
                    </label>
                    
                    <div id="<portlet:namespace />uploadError" style="color: #ef4444; display: none; font-size: 0.85rem; font-weight: 600; margin-top: 0.75rem; text-align: center;"></div>
                    
                    <button class="btn-upload" type="submit">Upload & Index Document</button>
                </form>
            </div>
            
            <hr style="border: 0; border-top: 1px solid #e2e8f0; margin: 0;">

            <div>
                <h3 style="font-size: 1.1rem; font-weight: 800; margin-bottom: 1rem; color: #0f172a;">Indexed Files</h3>
                <div class="indexed-item">
                    <i class="fa-solid fa-book"></i>
                    <div>
                        <strong style="font-size: 0.95rem; color: #0f172a; display: block;">GenC Guide 2025</strong>
                        <span style="font-size: 0.8rem; color: #10b981; font-weight: 700;">Ready for querying</span>
                    </div>
                </div>
            </div>
        </aside>

        <!-- Main Chat -->
        <main class="ai-card chat-panel">
            
            <div class="suggestions">
                <button type="button" class="chip" onclick="document.getElementById('<portlet:namespace />question').value=this.innerText">What is the Attendance Health Score?</button>
                <button type="button" class="chip" onclick="document.getElementById('<portlet:namespace />question').value=this.innerText">Explain leave limits and policy.</button>
                <button type="button" class="chip" onclick="document.getElementById('<portlet:namespace />question').value=this.innerText">What are the standard working hours?</button>
            </div>

            <div class="chat-body" id="<portlet:namespace />chatBody">
                <div id="<portlet:namespace />chatStatus" style="font-style: italic; color: #94a3b8; font-size: 0.9rem; text-align: center; margin-top: 1rem;"></div>
                <div id="<portlet:namespace />answerBox"></div>
            </div>

            <div class="input-group">
                <input type="text" id="<portlet:namespace />question" class="chat-input" placeholder="Ask anything about HR policy..." autocomplete="off" />
                <button type="button" id="<portlet:namespace />askButton" class="send-btn"><i class="fa-solid fa-paper-plane"></i></button>
            </div>

        </main>
    </div>
</div>

<script>
(function () {
    var uploadForm = document.getElementById('<portlet:namespace />uploadForm');
    var pdfFileInput = document.getElementById('<portlet:namespace />pdfFile');
    var uploadError = document.getElementById('<portlet:namespace />uploadError');

    if (uploadForm && pdfFileInput && uploadError) {
        uploadForm.addEventListener('submit', function (event) {
            uploadError.style.display = 'none';
            if (!pdfFileInput.files || pdfFileInput.files.length === 0) {
                event.preventDefault(); 
                uploadError.innerHTML = 'Please select a PDF file.'; 
                uploadError.style.display = 'block'; 
                return;
            }
        });
    }

    var askButton = document.getElementById('<portlet:namespace />askButton');
    var questionInput = document.getElementById('<portlet:namespace />question');
    var chatStatus = document.getElementById('<portlet:namespace />chatStatus');
    var answerBox = document.getElementById('<portlet:namespace />answerBox');

    if (!askButton || !questionInput || !chatStatus || !answerBox) return;

    askButton.addEventListener('click', askQuestion);
    
    questionInput.addEventListener('keydown', function (e) { 
        if (e.key === 'Enter') { 
            e.preventDefault(); 
            askQuestion(); 
        } 
    });

    function askQuestion() {
        var question = questionInput.value;
        if (!question.trim()) return;

        askButton.disabled = true; 
        askButton.innerHTML = '<i class="fa-solid fa-ellipsis"></i>';
        chatStatus.innerHTML = 'Searching policy documents...';

        var requestURL = '${chatURL}' + '&<portlet:namespace />question=' + encodeURIComponent(question);

        fetch(requestURL)
            .then(function(res) { 
                return res.json(); 
            })
            .then(function(data) {
                chatStatus.innerHTML = '';
                
                var userBubble = '<div class="msg-row msg-user">' +
                                 '<div class="avatar"><i class="fa-solid fa-user"></i></div>' +
                                 '<div class="bubble">' +
                                 '<div class="lbl">You</div>' +
                                 escapeHtml(question) +
                                 '</div></div>';
                
                var botBubble = '<div class="msg-row msg-sys">' +
                                '<div class="avatar"><i class="fa-solid fa-robot"></i></div>' +
                                '<div class="bubble">' +
                                '<div class="lbl">AI Assistant</div>' +
                                escapeHtml(data.answer || data.error) +
                                '<div id="<portlet:namespace />sourcesContainer"></div>' +
                                '</div></div>';

                answerBox.innerHTML += userBubble + botBubble;

                if (data.sources && data.sources.length > 0) {
                    var sourcesHtml = '<div style="font-size:0.8rem; color:#64748b; margin-top:1rem; border-top:1px solid #e2e8f0; padding-top:0.5rem;"><strong>Cited Sources:</strong><br/>';
                    data.sources.forEach(function(s) { 
                        sourcesHtml += '<i class="fa-solid fa-file-pdf"></i> ' + escapeHtml(s.documentTitle) + ' (Page ' + escapeHtml(String(s.pageNumber)) + ')<br/>';
                    });
                    document.getElementById('<portlet:namespace />sourcesContainer').innerHTML = sourcesHtml + '</div>';
                }
                
                questionInput.value = '';
                var chatBody = document.getElementById('<portlet:namespace />chatBody');
                chatBody.scrollTop = chatBody.scrollHeight;
            })
            .catch(function() { 
                chatStatus.innerHTML = '<span style="color:#ef4444;">Connection failed. Please try again.</span>';
            })
            .finally(function() { 
                askButton.disabled = false; 
                askButton.innerHTML = '<i class="fa-solid fa-paper-plane"></i>'; 
            });
    }

    function escapeHtml(val) { 
        return val ? val.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;') : ''; 
    }
})();
</script>