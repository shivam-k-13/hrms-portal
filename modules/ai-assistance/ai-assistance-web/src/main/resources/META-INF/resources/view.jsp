<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<portlet:actionURL name="/ai/upload_pdf" var="uploadPDFURL" />
<portlet:resourceURL id="/ai/chat" var="chatURL" />

<div class="ai-assistance-web">
<div class="hrms-ai-wrapper">

    <div class="hrms-ai-hero">
        <div>
            <div class="hrms-ai-badge">AI Powered HR Assistant</div>
            <h1>HRMS AI Assistant</h1>
            <p>
                Upload HR policy documents and ask questions about attendance,
                leave, training, payroll, and onboarding.
            </p>
        </div>
        <div class="hrms-ai-hero-icon">🤖</div>
    </div>

    <div class="hrms-ai-layout">

        <aside class="hrms-ai-sidebar">
            <div class="hrms-card">
                <h2>Documents</h2>
                <p class="muted-text">
                    Upload PDF documents for AI-based question answering.
                </p>

                <form id="<portlet:namespace />uploadForm" action="${uploadPDFURL}" method="POST" enctype="multipart/form-data">
                    <label class="upload-box" for="<portlet:namespace />pdfFile">
                        <span class="upload-icon">📄</span>
                        <span class="upload-title">Choose PDF Document</span>
                        <span class="upload-subtitle">Only PDF files are supported (Max 10MB)</span>
                        <input 
                            type="file" 
                            name="<portlet:namespace />pdfFile" 
                            id="<portlet:namespace />pdfFile" 
                            accept=".pdf,application/pdf" />
                    </label>

                    <div id="<portlet:namespace />uploadError" style="color:red; display:none; margin: 10px 0; font-size: 14px;"></div>

                    <button class="primary-btn full-width" type="submit">
                        Upload PDF
                    </button>
                </form>
            </div>

            <div class="hrms-card">
                <h3>Indexed Documents</h3>
                <div class="document-item">
                    <div class="doc-icon">📘</div>
                    <div>
                        <strong>GenC Guide 2025</strong>
                        <p>Indexed successfully</p>
                    </div>
                    <span class="status-pill">Ready</span>
                </div>
            </div>
        </aside>

        <main class="hrms-ai-chat-panel">

            <div class="chat-header">
                <div>
                    <h2>Ask HRMS AI</h2>
                    <p>Get instant answers from uploaded HR documents.</p>
                </div>
            </div>

            <div class="suggestion-list">
                <button type="button" class="suggestion-chip" onclick="document.getElementById('<portlet:namespace />question').value=this.innerText">What is Attendance Health Score?</button>
                <button type="button" class="suggestion-chip" onclick="document.getElementById('<portlet:namespace />question').value=this.innerText">How many leaves can an employee take?</button>
                <button type="button" class="suggestion-chip" onclick="document.getElementById('<portlet:namespace />question').value=this.innerText">What are working hour guidelines?</button>
                <button type="button" class="suggestion-chip" onclick="document.getElementById('<portlet:namespace />question').value=this.innerText">What is graduation criteria?</button>
            </div>

            <div class="chat-body" id="<portlet:namespace />chatBody">
                <div id="<portlet:namespace />chatStatus" style="margin-bottom: 10px; font-style: italic; color: #555;"></div>
                <div id="<portlet:namespace />answerBox"></div>
                <div id="<portlet:namespace />sourcesBox"></div>
            </div>

            <div class="chat-input-form">
                <input
                    type="text"
                    id="<portlet:namespace />question"
                    class="chat-input"
                    placeholder="Ask anything about HR policy, attendance, leave..."
                    autocomplete="off"
                />
                <button type="button" id="<portlet:namespace />askButton" class="send-btn">
                    ➤
                </button>
            </div>

        </main>

    </div>
</div>
<div/>

<script>
(function () {
    var uploadForm = document.getElementById('<portlet:namespace />uploadForm');
    var pdfFileInput = document.getElementById('<portlet:namespace />pdfFile');
    var uploadError = document.getElementById('<portlet:namespace />uploadError');

    var maxUploadSizeBytes = 10 * 1024 * 1024; // 10 MB Limit

    if (uploadForm && pdfFileInput && uploadError) {
        uploadForm.addEventListener('submit', function (event) {
            uploadError.style.display = 'none';
            uploadError.innerHTML = '';

            if (!pdfFileInput.files || pdfFileInput.files.length === 0) {
                event.preventDefault();
                uploadError.innerHTML = 'Please select a PDF file.';
                uploadError.style.display = 'block';
                return;
            }

            var selectedFile = pdfFileInput.files[0];
            var fileName = selectedFile.name.toLowerCase();

            if (!fileName.endsWith('.pdf')) {
                event.preventDefault();
                uploadError.innerHTML = 'Only PDF files are allowed.';
                uploadError.style.display = 'block';
                return;
            }

            if (selectedFile.size > maxUploadSizeBytes) {
                event.preventDefault();
                uploadError.innerHTML = 'PDF file is too large. Maximum allowed size is 10 MB.';
                uploadError.style.display = 'block';
                return;
            }
        });
    }

    var askButton = document.getElementById('<portlet:namespace />askButton');
    var questionInput = document.getElementById('<portlet:namespace />question');
    var chatStatus = document.getElementById('<portlet:namespace />chatStatus');
    var answerBox = document.getElementById('<portlet:namespace />answerBox');
    var sourcesBox = document.getElementById('<portlet:namespace />sourcesBox');

    if (!askButton || !questionInput || !chatStatus || !answerBox || !sourcesBox) {
        return;
    }

    askButton.addEventListener('click', askQuestion);

    questionInput.addEventListener('keydown', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            askQuestion();
        }
    });

    function askQuestion() {
        var question = questionInput.value;

        chatStatus.innerHTML = '';
        answerBox.innerHTML = '';
        sourcesBox.innerHTML = '';

        if (!question || question.trim().length === 0) {
            chatStatus.innerHTML = '<span style="color:red;">Please enter a question.</span>';
            return;
        }

        askButton.disabled = true;
        askButton.innerHTML = '...';

        chatStatus.innerHTML = 'Finding relevant content and generating answer...';

        // Secure Liferay-Namespaced URL assembly mapping
        var requestURL = '${chatURL}' + '&<portlet:namespace />question=' + encodeURIComponent(question);

        fetch(requestURL)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                if (!data.success) {
                    chatStatus.innerHTML = '<span style="color:red;">' + escapeHtml(data.error || 'Unable to generate answer.') + '</span>';
                    return;
                }

                chatStatus.innerHTML = '';

                // Build User Request UI bubble trace dynamically
                var userChatBubble = 
                    '<div class="message-row user-message">' +
                    '   <div class="message-avatar">👤</div>' +
                    '   <div class="message-bubble">' +
                    '       <div class="message-label">You</div>' +
                    '       <p>' + escapeHtml(question) + '</p>' +
                    '   </div>' +
                    '</div>';

                // Render dynamic Markdown block inside system standard response cards
                var systemResponseBubble = 
                    '<div class="message-row assistant-message">' +
                    '   <div class="message-avatar">🤖</div>' +
                    '   <div class="message-bubble">' +
                    '       <div class="message-label">HRMS AI Assistant</div>' +
                    '       <div class="answer-content" style="white-space:pre-wrap; line-height:1.5;">' + escapeHtml(data.answer) + '</div>' +
                    '       <div class="source-section" id="<portlet:namespace />sourcesContainer"></div>' +
                    '   </div>' +
                    '</div>';

                answerBox.innerHTML = userChatBubble + systemResponseBubble;

                // Build Source citation blocks if passed back inside payload index arrays
                var sourcesContainer = document.getElementById('<portlet:namespace />sourcesContainer');
                if (sourcesContainer && data.sources && data.sources.length > 0) {
                    var sourcesHtml = '<h4>Sources</h4>';
                    data.sources.forEach(function (source) {
                        sourcesHtml += 
                            '<div class="source-card">' +
                            '   <span>📄 ' + escapeHtml(source.documentTitle) + '</span>' +
                            '   <strong>Page ' + escapeHtml(String(source.pageNumber)) + '</strong>' +
                            '</div>';
                    });
                    sourcesContainer.innerHTML = sourcesHtml;
                }
                
                questionInput.value = ''; // Clean input fields natively after response stream finishes
            })
            .catch(function (error) {
                chatStatus.innerHTML = '<span style="color:red;">Chat request failed.</span>';
                console.error(error);
            })
            .finally(function () {
                askButton.disabled = false;
                askButton.innerHTML = '➤';
            });
    }

    function escapeHtml(value) {
        if (!value) return '';
        return value
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#039;');
    }
})();
</script>