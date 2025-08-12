'use client';

import { useState } from 'react';

export default function ContactForm({ 
  title = "메시지 보내기",
  icon = "✉️",
  placeholder = {
    name: "이름을 입력하세요",
    email: "이메일을 입력하세요",
    subject: "제목을 입력하세요",
    message: "메시지를 입력해주세요..."
  },
  successMessage = {
    title: "메시지가 전송되었습니다!",
    description: "빠른 시일 내에 답변드리겠습니다.",
    button: "새 메시지 작성"
  },
  submitButton = "메시지 보내기",
  className = ''
}) {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: '',
    message: ''
  });

  const [isSubmitted, setIsSubmitted] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setIsSubmitted(true);
    // 실제로는 여기서 서버로 데이터를 전송합니다
    console.log('폼 데이터:', formData);
  };

  return (
    <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 ${className}`}>
      <h2 className="text-2xl font-bold mb-8 text-white flex items-center">
        <span className="text-3xl mr-3">{icon}</span>
        {title}
      </h2>
      
      {isSubmitted ? (
        <div className="text-center py-12">
          <div className="w-20 h-20 mx-auto mb-6 bg-gradient-to-r from-emerald-500 to-teal-500 rounded-full flex items-center justify-center">
            <svg className="w-10 h-10 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
            </svg>
          </div>
          <h3 className="text-2xl font-bold mb-4 text-white">{successMessage.title}</h3>
          <p className="text-gray-300 mb-8">{successMessage.description}</p>
          <button 
            onClick={() => {
              setIsSubmitted(false);
              setFormData({ name: '', email: '', subject: '', message: '' });
            }}
            className="px-8 py-3 bg-gradient-to-r from-blue-500 to-cyan-500 text-white rounded-xl hover:from-blue-600 hover:to-cyan-600 transition-all duration-300 font-medium"
          >
            {successMessage.button}
          </button>
        </div>
      ) : (
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm font-medium mb-2 text-white">이름 *</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
              className="w-full px-4 py-3 bg-gray-700/50 border border-gray-600/50 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent text-white placeholder-gray-400 backdrop-blur-md"
              placeholder={placeholder.name}
              required
            />
          </div>
          
          <div>
            <label className="block text-sm font-medium mb-2 text-white">이메일 *</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleInputChange}
              className="w-full px-4 py-3 bg-gray-700/50 border border-gray-600/50 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent text-white placeholder-gray-400 backdrop-blur-md"
              placeholder={placeholder.email}
              required
            />
          </div>
          
          <div>
            <label className="block text-sm font-medium mb-2 text-white">제목 *</label>
            <input
              type="text"
              name="subject"
              value={formData.subject}
              onChange={handleInputChange}
              className="w-full px-4 py-3 bg-gray-700/50 border border-gray-600/50 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent text-white placeholder-gray-400 backdrop-blur-md"
              placeholder={placeholder.subject}
              required
            />
          </div>
          
          <div>
            <label className="block text-sm font-medium mb-2 text-white">메시지 *</label>
            <textarea
              name="message"
              value={formData.message}
              onChange={handleInputChange}
              rows="5"
              className="w-full px-4 py-3 bg-gray-700/50 border border-gray-600/50 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent text-white placeholder-gray-400 backdrop-blur-md resize-none"
              placeholder={placeholder.message}
              required
            />
          </div>
          
          <button
            type="submit"
            className="w-full px-6 py-4 bg-gradient-to-r from-blue-500 to-cyan-500 text-white rounded-xl hover:from-blue-600 hover:to-cyan-600 transition-all duration-300 font-medium text-lg shadow-lg hover:shadow-xl transform hover:scale-105"
          >
            {submitButton}
          </button>
        </form>
      )}
    </div>
  );
} 