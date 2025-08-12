'use client'

import { useState, useEffect, useCallback } from 'react'
import { createProfile, updateProfile, getProfileByUserId } from '@/lib/profiles'
import Card from '@/components/ui/Card'
import Button from '@/components/ui/Button'

export default function ProfileForm({ userId, profileData = null, mode = 'create', onSuccess }) {
  const [formData, setFormData] = useState({ username: '', intro: '' })
  const [loading, setLoading] = useState(false)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  const loadProfile = useCallback(async () => {
    try {
      const { data, error } = await getProfileByUserId(userId)
      if (error) throw error
      
      if (data) {
        setFormData({
          username: data.username || '',
          intro: data.intro || ''
        })
      }
    } catch (error) {
      console.error('프로필 로드 오류:', error)
    }
  }, [userId])

  useEffect(() => {
    if (mode === 'edit') {
      if (profileData) {
        // 전달받은 프로필 데이터 사용
        setFormData({
          username: profileData.username || '',
          intro: profileData.intro || ''
        })
      } else if (userId) {
        // userId로 프로필 로드
        loadProfile()
      }
    }
  }, [mode, userId, profileData, loadProfile])

  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }))
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)
    setMessage('')
    setError('')

    try {
      let result
      
      if (mode === 'create') {
        result = await createProfile(userId, formData.username, formData.intro)
      } else {
        // edit 모드에서는 프로필 ID 사용
        const profileId = profileData?.id
        if (!profileId) {
          throw new Error('프로필 ID가 없습니다.')
        }
        result = await updateProfile(profileId, formData)
      }

      if (result.error) {
        setError(result.error)
      } else {
        const successMessage = mode === 'create' ? '동기들에게 첫 인사 완료!' : '자기소개가 업데이트되었어요!'
        setMessage(successMessage)
        onSuccess?.(result.data)
      }
    } catch (err) {
      setError(err.message || '오류가 발생했습니다.')
    } finally {
      setLoading(false)
    }
  }

  const isEdit = mode === 'edit'

  return (
    <Card>
      <div className="p-6">
        <h2 className="text-2xl font-bold text-gray-900 mb-6">
          {isEdit ? '자기소개 수정하기' : '동기들에게 첫 인사 남기기'}
        </h2>
        
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label htmlFor="username" className="block text-sm font-medium text-gray-700 mb-2">
              이름 또는 닉네임 *
            </label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              required
              className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
              placeholder="동기들이 부를 이름을 적어주세요"
            />
          </div>

          <div>
            <label htmlFor="intro" className="block text-sm font-medium text-gray-700 mb-2">
              자기소개 *
            </label>
            <textarea
              id="intro"
              name="intro"
              value={formData.intro}
              onChange={handleChange}
              required
              rows={4}
              className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
              placeholder="안녕하세요! 저는... (MBTI, 취미, 출신지, 좋아하는 음식 등 뭐든 좋아요)"
            />
          </div>

          {error && (
            <div className="text-red-600 text-sm">{error}</div>
          )}

          {message && (
            <div className="text-green-600 text-sm">{message}</div>
          )}

          <div className="flex justify-end space-x-3">
            <Button
              type="submit"
              disabled={loading}
            >
              {loading ? '등록 중...' : (isEdit ? '수정 완료!' : '첫 인사 남기기')}
            </Button>
          </div>
        </form>
      </div>
    </Card>
  )
} 