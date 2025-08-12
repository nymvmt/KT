'use client'

import { useState, useEffect, useCallback } from 'react'
import { getAllProfiles, checkProfileExists } from '@/lib/profiles'
import { useAuth } from '@/contexts/AuthContext'
import Card from '@/components/ui/Card'
import Button from '@/components/ui/Button'
import ProfileCard from '@/components/ProfileCard'
import ProfileForm from '@/components/ProfileForm'
import { ROUTES } from '@/constants/routes'

export default function ProfilesPage() {
  const { user, loading: authLoading } = useAuth()
  const [profiles, setProfiles] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [editingProfile, setEditingProfile] = useState(null)
  const [userHasProfile, setUserHasProfile] = useState(false)

  const loadData = useCallback(async () => {
    try {
      if (user) {
        const { exists } = await checkProfileExists(user.id)
        setUserHasProfile(exists)
      }

      const { data, error } = await getAllProfiles()
      if (error) throw error
      
      setProfiles(data || [])
    } catch (error) {
      console.error('데이터 로드 오류:', error)
    } finally {
      setLoading(false)
    }
  }, [user])

  useEffect(() => {
    if (!authLoading) {
      loadData()
    }
  }, [authLoading, loadData])

  const handleProfileSuccess = (newProfile) => {
    if (editingProfile) {
      // 수정된 프로필로 업데이트
      setProfiles(prev => 
        prev.map(p => p.id === newProfile[0].id ? newProfile[0] : p)
      )
      setEditingProfile(null)
    } else {
      // 새로운 프로필 추가
      setProfiles(prev => [newProfile[0], ...prev])
      setUserHasProfile(true)
    }
    setShowForm(false)
  }

  const handleDelete = (profileId) => {
    setProfiles(prev => prev.filter(p => p.id !== profileId))
    
    // 삭제된 프로필이 현재 사용자의 프로필인지 확인
    const deletedProfile = profiles.find(p => p.id === profileId)
    if (user && deletedProfile && deletedProfile.user_id === user.id) {
      setUserHasProfile(false)
    }
  }

  const handleEdit = (profile) => {
    setEditingProfile(profile)
    setShowForm(true)
  }

  const handleCreateNew = () => {
    setEditingProfile(null)
    setShowForm(true)
  }

  if (authLoading || loading) {
    return (
      <div className="flex items-center justify-center">
        <div className="text-lg">동기들을 찾고 있어요...</div>
      </div>
    )
  }

  return (
    <>
      {/* 헤더 */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-4">
          동기들과 인사해요!
        </h1>
        <p className="text-gray-600 mb-6">
          같이 입사한 동기들의 이야기를 들어보고, 나만의 자기소개도 남겨보세요! 
          소소한 취미부터 MBTI까지, 서로에 대해 알아가는 시간
        </p>
        
        {/* 로그인한 사용자를 위한 프로필 만들기 버튼 */}
        {user && !showForm && (
          <Card className={`p-4 mb-6 ${
            userHasProfile 
              ? 'bg-green-50 border-green-200' 
              : 'bg-blue-50 border-blue-200'
          }`}>
            <div className="flex items-center justify-between">
              <div>
                <h3 className={`text-lg font-medium ${
                  userHasProfile ? 'text-green-900' : 'text-blue-900'
                }`}>
                  {userHasProfile ? '자기소개 완료!' : '아직 자기소개를 안 하셨네요!'}
                </h3>
                <p className={userHasProfile ? 'text-green-700' : 'text-blue-700'}>
                  {userHasProfile 
                    ? '다른 동기들도 구경해보고, 언제든 내 소개를 수정할 수 있어요.'
                    : '첫 인사를 나눠볼까요? 간단한 소개만 해도 좋아요!'
                  }
                </p>
              </div>
              <Button
                variant={userHasProfile ? 'success' : 'primary'}
                onClick={handleCreateNew}
              >
                {userHasProfile ? '새로운 소개 쓰기' : '첫 인사 남기기'}
              </Button>
            </div>
          </Card>
        )}

        {/* 로그인하지 않은 사용자를 위한 안내 */}
        {!user && (
          <Card className="p-4 mb-6 bg-gray-50 border-gray-200">
            <div className="flex items-center justify-between">
              <div>
                <h3 className="text-lg font-medium text-gray-900">
                  동기들과 인사하려면 로그인이 필요해요
                </h3>
                <p className="text-gray-700">
                  신입사원 등록을 먼저 해주세요!
                </p>
              </div>
              <Button href={ROUTES.LOGIN}>
                로그인하기
              </Button>
            </div>
          </Card>
        )}
      </div>

      {/* 프로필 폼 */}
      {showForm && user && (
        <div className="mb-8">
          <ProfileForm
            userId={user.id}
            profileData={editingProfile}
            mode={editingProfile ? 'edit' : 'create'}
            onSuccess={handleProfileSuccess}
          />
          <div className="mt-4 text-center">
            <Button
              variant="outline"
              size="sm"
              onClick={() => setShowForm(false)}
            >
              나중에 할게요
            </Button>
          </div>
        </div>
      )}

      {/* 프로필 목록 */}
      <div className="space-y-6">
        {profiles.length === 0 ? (
          <div className="text-center py-12">
            <h3 className="text-xl font-medium text-gray-900 mb-2">
              아직 아무도 인사를 안 했네요!
            </h3>
            <p className="text-gray-600">
              첫 번째 자기소개의 주인공이 되어보세요
            </p>
            {user && (
              <Button
                onClick={handleCreateNew}
                className="mt-4"
                size="lg"
              >
                첫 인사 남기기
              </Button>
            )}
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {profiles.map((profile) => (
              <ProfileCard
                key={profile.id}
                profile={profile}
                currentUserId={user?.id}
                onDelete={handleDelete}
                onEdit={handleEdit}
              />
            ))}
          </div>
        )}
      </div>
    </>
  )
} 